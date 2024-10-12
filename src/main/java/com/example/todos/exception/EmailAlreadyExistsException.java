package com.example.todos.exception;


public class EmailAlreadyExistsException extends TodoAPIException {

  public EmailAlreadyExistsException() {
    super(400, "Bad request", "Email already exists.");
  }
}
