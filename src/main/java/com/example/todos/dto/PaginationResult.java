package com.example.todos.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResult<T> {

  List<T> data;
  private int currentPage;
  private int totalElements;
  private int totalPages;
}
