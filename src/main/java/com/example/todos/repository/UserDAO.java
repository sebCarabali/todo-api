package com.example.todos.repository;

import com.example.todos.model.User;
import java.util.Optional;

public interface UserDAO {

  boolean validateUniqueEmail(String email);

  boolean validateUniqueUsername(String username);

  void saveUser(User user);

  Optional<User> loadByUsername(String username);
}
