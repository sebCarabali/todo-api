package com.example.todos.controller;

import com.example.todos.dto.RegisterUserRequestDTO;
import com.example.todos.dto.RegisterUserResponseDTO;
import com.example.todos.model.User;
import com.example.todos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping(value = "/register-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegisterUserResponseDTO> registerUser(
      @RequestBody RegisterUserRequestDTO registerUserDTO) {
    User user = User.builder()
        .username(registerUserDTO.getUsername())
        .email(registerUserDTO.getEmail())
        .password(registerUserDTO.getPassword())
        .build();
    String token = authService.registerUser(user);
    return ResponseEntity.ok(new RegisterUserResponseDTO(token));
  }
}
