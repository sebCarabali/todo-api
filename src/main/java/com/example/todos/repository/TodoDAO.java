package com.example.todos.repository;

import com.example.todos.model.Todo;
import com.example.todos.repository.filter.Filter;
import com.example.todos.repository.filter.FilterBuilder;
import java.util.List;

public interface TodoDAO {

  Todo create(Todo todo);

  void update(Todo todo);

  void delete(int id);

  List<Todo> findTodos(FilterBuilder filterBuilder, int page, int size);

  int countTodos(FilterBuilder filterBuilder);
}
