package com.example.todos.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TodoAPIException extends RuntimeException {

  private final int statusCode;
  private final String statusMessage;
  private final String text;
}
