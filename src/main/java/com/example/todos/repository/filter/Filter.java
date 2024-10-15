package com.example.todos.repository.filter;

import java.util.List;

public interface Filter {
  String toSql();
  List<Object> getParams();
}
