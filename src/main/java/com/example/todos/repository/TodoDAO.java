package com.example.todos.repository;

import com.example.todos.model.Todo;

public interface TodoDAO {

  Todo create(Todo todo);

  void update(Todo todo);
}
