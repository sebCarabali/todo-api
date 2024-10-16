package com.example.todos.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo implements Serializable {

  private Integer id;
  private String title;
  private String description;
  private Long userId;
}
