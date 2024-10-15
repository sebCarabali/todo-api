package com.example.todos.service.impl;

import com.example.todos.dto.PaginationResult;
import com.example.todos.dto.TodoFilter;
import com.example.todos.model.Todo;
import com.example.todos.model.User;
import com.example.todos.repository.TodoDAO;
import com.example.todos.repository.UserDAO;
import com.example.todos.repository.filter.DescriptionFilter;
import com.example.todos.repository.filter.FilterBuilder;
import com.example.todos.repository.filter.TitleFilter;
import com.example.todos.security.SecurityUtil;
import com.example.todos.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

  private final TodoDAO todoDAO;
  private final UserDAO userDAO;
  private final AuthenticationManager authenticationManager;

  @Override
  @Transactional
  public Todo create(Todo todo) {
    String username = SecurityUtil.getCurrentUsername();
    User user = userDAO.loadByUsername(username).orElseThrow();
    todo.setUserId(user.getId());
    return todoDAO.create(todo);
  }

  @Override
  @Transactional
  public Todo update(Todo todo) {
    todoDAO.update(todo);
    return todo;
  }

  @Transactional
  @Override
  public void delete(int todoId) {
    todoDAO.delete(todoId);
  }

  @Override
  public PaginationResult<Todo> findTodos(TodoFilter filter, int page, int size) {
    FilterBuilder filterBuilder = new FilterBuilder();
    if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
      filterBuilder.addFilter(new TitleFilter(filter.getTitle()));
    }

    if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
      filterBuilder.addFilter(new DescriptionFilter(filter.getDescription()));
    }

    List<Todo> todos = todoDAO.findTodos(filterBuilder, page, size);
    int totalElements = todoDAO.countTodos(filterBuilder);
    int totalPages = (int) Math.ceil((double) totalElements / size);
    return new PaginationResult<>(todos, page, totalElements, totalPages);
  }
}
