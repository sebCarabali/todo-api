package com.example.todos.dto.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CreateTodoRequestDTO implements Serializable {

  private String title;
  private String description;
}
