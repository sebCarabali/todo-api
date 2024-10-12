package com.example.todos.repository.impl;

import com.example.todos.model.Todo;
import com.example.todos.repository.TodoDAO;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoDAOImpl implements TodoDAO {

  private static final String INSERT_TODO = "INSERT INTO todos (title, description, user_id) VALUES (?, ?, ?)";
  private static final String UPDATE_TODO = "UPDATE todos SET title = ?, description = ? WHERE id = ?";

  private final JdbcTemplate jdbcTemplate;

  @Override
  public Todo create(Todo todo) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(INSERT_TODO, Statement.RETURN_GENERATED_KEYS);
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
      PreparedStatement ps = con.prepareStatement(UPDATE_TODO, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, todo.getTitle());
      ps.setString(2, todo.getDescription());
      ps.setLong(3, todo.getId());
      return ps;
    });
  }
}
