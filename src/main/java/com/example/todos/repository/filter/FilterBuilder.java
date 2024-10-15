package com.example.todos.repository.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterBuilder {

  private final List<Filter> filters;

  public FilterBuilder() {
    filters = new ArrayList<>();
  }

  public void addFilter(Filter filter) {
    this.filters.add(filter);
  }

  public String toSql() {
      StringBuilder sql = new StringBuilder(" WHERE 1=1");
      for(Filter filter: filters) {
        sql.append(filter.toSql());
      }
      return sql.toString();
  }

  public List<Object> buildParams() {
    List<Object> params = new ArrayList<>();
    for (Filter filter : filters) {
      params.addAll(filter.getParams());
    }
    return params;
  }
}
