package com.example.todos.exception;

import com.example.todos.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoAPIExceptionHandler {

  @ExceptionHandler(TodoAPIException.class)
  public ResponseEntity<ErrorResponse> handleTodoApiExceptions(TodoAPIException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(), ex.getStatusMessage(),
        ex.getText());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Internal Server Error",
        ex.getMessage()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
