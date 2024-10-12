package com.example.todos.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todo implements Serializable {

  private Integer id;
  private String title;
  private String description;
  private Long userId;
}
