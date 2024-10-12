package com.example.todos.exception;

public class UsernameAlreadyExistsException extends TodoAPIException {

  public UsernameAlreadyExistsException() {
    super(400 , "Bad request", "Username already exist.");
  }
}
