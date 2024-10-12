package com.example.todos.controller;

import com.example.todos.dto.LoginRequestDTO;
import com.example.todos.dto.RegisterUserRequestDTO;
import com.example.todos.dto.JwtResponse;
import com.example.todos.model.User;
import com.example.todos.security.JwtUtil;
import com.example.todos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @PostMapping(value = "/register-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JwtResponse> registerUser(
      @RequestBody RegisterUserRequestDTO registerUserDTO) {
    User user = User.builder()
        .username(registerUserDTO.getUsername())
        .email(registerUserDTO.getEmail())
        .password(registerUserDTO.getPassword())
        .build();
    String token = authService.registerUser(user);
    return ResponseEntity.ok(new JwtResponse(token));
  }

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JwtResponse> login(@RequestBody LoginRequestDTO loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtUtil.encode(loginRequest.getUsername());
    return ResponseEntity.ok(new JwtResponse(token));
  }
}
