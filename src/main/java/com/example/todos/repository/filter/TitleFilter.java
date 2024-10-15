package com.example.todos.repository.filter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TitleFilter implements Filter {

  private final String title;

  @Override
  public List<Object> getParams() {
    return !Objects.isNull(title) && !title.isEmpty() ? Collections.singletonList("%" + title + "%")
        : Collections.emptyList();
  }

  @Override
  public String toSql() {
    return !Objects.isNull(title) && !title.isEmpty() ? " AND title LIKE ?" : "";
  }
}
