package com.example.todos.repository.filter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DescriptionFilter implements Filter {

  private final String description;

  @Override
  public List<Object> getParams() {
    return !Objects.isNull(description) && !description.isEmpty() ? Collections.singletonList("%" + description + "%")
        : Collections.emptyList();
  }

  @Override
  public String toSql() {
    return !Objects.isNull(description) && !description.isEmpty() ? " AND description LIKE ?" : "";
  }
}
