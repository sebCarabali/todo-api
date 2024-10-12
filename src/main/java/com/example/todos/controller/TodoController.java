package com.example.todos.controller;

import com.example.todos.dto.dto.CreateTodoRequestDTO;
import com.example.todos.dto.dto.CreateTodoResponseDTO;
import com.example.todos.model.Todo;
import com.example.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateTodoResponseDTO> create(@RequestBody CreateTodoRequestDTO createTodoRequest) {
    Todo todo = Todo.builder()
        .title(createTodoRequest.getTitle())
        .description(createTodoRequest.getDescription())
        .build();

    Todo savedTodo = todoService.create(todo);
    return ResponseEntity.ok(new CreateTodoResponseDTO(savedTodo.getId(), savedTodo.getTitle(), savedTodo.getDescription()));
  }
}
