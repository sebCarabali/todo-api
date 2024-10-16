package com.example.todos.dto.user;

import java.io.Serializable;
import lombok.Data;

@Data
public class RegisterUserRequestDTO implements Serializable {

  private String email;
  private String username;
  private String password;
}
