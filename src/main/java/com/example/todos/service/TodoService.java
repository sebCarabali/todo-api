package com.example.todos.service;

import com.example.todos.dto.PaginationResult;
import com.example.todos.dto.TodoFilter;
import com.example.todos.model.Todo;

public interface TodoService {

  Todo create(Todo todo);

  Todo update(Todo todo);

  void delete(int todoId);

  PaginationResult<Todo> findTodos(TodoFilter filter, int page, int size);
}
