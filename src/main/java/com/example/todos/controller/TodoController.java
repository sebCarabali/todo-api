package com.example.todos.controller;

import com.example.todos.dto.PaginationResult;
import com.example.todos.dto.TodoFilter;
import com.example.todos.dto.todo.CreateTodoRequestDTO;
import com.example.todos.dto.todo.CreateTodoResponseDTO;
import com.example.todos.model.Todo;
import com.example.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateTodoResponseDTO> create(
      @RequestBody CreateTodoRequestDTO createTodoRequest) {
    Todo todo = Todo.builder()
        .title(createTodoRequest.getTitle())
        .description(createTodoRequest.getDescription())
        .build();

    Todo savedTodo = todoService.create(todo);
    return ResponseEntity.ok(new CreateTodoResponseDTO(savedTodo.getId(), savedTodo.getTitle(),
        savedTodo.getDescription()));
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateTodoResponseDTO> update(@PathVariable("id") int id,
      @RequestBody CreateTodoRequestDTO createTodoRequest) {
    Todo todo = Todo.builder()
        .id(id)
        .title(createTodoRequest.getTitle())
        .description(createTodoRequest.getDescription())
        .build();

    Todo savedTodo = todoService.update(todo);
    return ResponseEntity.ok(new CreateTodoResponseDTO(savedTodo.getId(), savedTodo.getTitle(),
        savedTodo.getDescription()));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    todoService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<PaginationResult<Todo>> findTodos(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "page", required = true, defaultValue = "1") int page,
      @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
    TodoFilter todoFilter = new TodoFilter(title, description);
    PaginationResult<Todo> result = todoService.findTodos(todoFilter, page, size);
    return ResponseEntity.ok(result);
  }
}
