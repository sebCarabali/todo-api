package com.example.todos.service.impl;

import com.example.todos.model.Todo;
import com.example.todos.model.User;
import com.example.todos.repository.TodoDAO;
import com.example.todos.repository.UserDAO;
import com.example.todos.security.SecurityUtil;
import com.example.todos.service.TodoService;
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

  @Override
  public void delete(int todoId) {

  }
}
