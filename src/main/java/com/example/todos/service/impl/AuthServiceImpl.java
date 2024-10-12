package com.example.todos.service.impl;

import com.example.todos.exception.EmailAlreadyExistsException;
import com.example.todos.exception.UsernameAlreadyExistsException;
import com.example.todos.model.User;
import com.example.todos.repository.UserDAO;
import com.example.todos.security.JwtUtil;
import com.example.todos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDAO userDAO;

  private final JwtUtil jwtUtil;

  @Override
  @Transactional
  public String registerUser(User user) {
    // Validate user data
    if (!userDAO.validateUniqueEmail(user.getEmail())) {
      throw new EmailAlreadyExistsException();
    }

    if(!userDAO.validateUniqueUsername(user.getUsername())) {
      throw new UsernameAlreadyExistsException();
    }
    // Register user data
    userDAO.saveUser(user);
    // Generate and return token
    return jwtUtil.encode(user);
  }
}
