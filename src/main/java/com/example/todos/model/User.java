package com.example.todos.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User implements Serializable {

  private Long id;
  private String username;
  private String password;
  private String email;
  private LocalDate createdAt;
}
