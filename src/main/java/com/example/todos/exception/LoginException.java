package com.example.todos.exception;

public class LoginException extends TodoAPIException {

  public LoginException(String text) {
    super(401, "Not authorized", text);
    System.out.println("Create login error message: " + text);
  }
}
