package com.example.todos.repository;

import com.example.todos.model.User;

public interface UserDAO {

  boolean validateUniqueEmail(String email);

  boolean validateUniqueUsername(String username);

  void saveUser(User user);
}
