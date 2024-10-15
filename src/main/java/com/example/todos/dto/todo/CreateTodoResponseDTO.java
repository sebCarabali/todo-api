package com.example.todos.dto.todo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTodoResponseDTO implements Serializable {

  private Integer id;
  private String title;
  private String description;
}
