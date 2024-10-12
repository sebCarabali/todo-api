package com.example.todos.service;

import com.example.todos.model.Todo;

public interface TodoService {

  Todo create(Todo todo);

  Todo update(Todo todo);

  void delete(int todoId);
}
