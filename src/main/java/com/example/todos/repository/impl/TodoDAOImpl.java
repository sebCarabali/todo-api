package com.example.todos.repository.impl;

import com.example.todos.model.Todo;
import com.example.todos.repository.TodoDAO;
import com.example.todos.repository.filter.Filter;
import com.example.todos.repository.filter.FilterBuilder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoDAOImpl implements TodoDAO {

  private static final String INSERT_QUERY = "INSERT INTO todos (title, description, user_id) VALUES (?, ?, ?)";
  private static final String UPDATE_QUERY = "UPDATE todos SET title = ?, description = ? WHERE id = ?";
  private static final String DELETE_QUERY = "DELETE FROM todos WHERE id = ?";
  private static final String SELECT_QUERY = "SELECT * FROM todos";
  private static final String COUNT_QUERY = "SELECT COUNT(*) FROM todos";

  private final JdbcTemplate jdbcTemplate;

  @Override
  public Todo create(Todo todo) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, todo.getTitle());
      ps.setString(2, todo.getDescription());
      ps.setLong(3, todo.getUserId());
      return ps;
    }, keyHolder);
    Map<String, Object> keys = keyHolder.getKeys();
    if (keys != null) {
      todo.setId((Integer) keys.get("id"));
    }
    return todo;
  }

  @Override
  public void update(Todo todo) {
    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, todo.getTitle());
      ps.setString(2, todo.getDescription());
      ps.setLong(3, todo.getId());
      return ps;
    });
  }

  @Override
  public void delete(int id) {
    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, id);
      return ps;
    });
  }
  private RowMapper<Todo> todoRowMapper = (rs, rowNum) -> {
    Todo todo = new Todo();
    todo.setId(rs.getInt("id"));
    todo.setTitle(rs.getString("title"));
    todo.setDescription(rs.getString("description"));
    todo.setUserId(rs.getLong("user_id"));
    return todo;
  };

  @Override
  public List<Todo> findTodos(FilterBuilder filterBuilder, int page, int size) {
    String sql = SELECT_QUERY + filterBuilder.toSql() + " LIMIT ? OFFSET ?";
    List<Object> params = filterBuilder.buildParams();
    int offset = (page  - 1) * size;
    params.add(size);
    params.add(offset);
    log.info("Sql: {}", sql);
    log.info("Params: {}", params);
    return jdbcTemplate.query(sql, todoRowMapper, params.toArray());
  }

  @Override
  public int countTodos(FilterBuilder filterBuilder) {
    String sql = COUNT_QUERY + filterBuilder.toSql();
    List<Object> params = filterBuilder.buildParams();
    log.info("Sql: {}", sql);
    log.info("Params: {}", params);
    return jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
  }
}
