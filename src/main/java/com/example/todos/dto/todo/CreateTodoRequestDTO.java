package com.example.todos.dto.todo;

import java.io.Serializable;
import lombok.Data;

@Data
public class CreateTodoRequestDTO implements Serializable {

  private String title;
  private String description;
}
