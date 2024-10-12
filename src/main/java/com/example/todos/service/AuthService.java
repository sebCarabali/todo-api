package com.example.todos.service;

import com.example.todos.model.User;

public interface AuthService {

  /**
   * Registers a new user in the system.
   * <p>
   * This method processes the registration of a new user by accepting a User object containing the
   * necessary user details. Upon successful registration, an authentication token is generated and
   * returned for the newly registered user, which can be used for subsequent authenticated
   * requests.
   *
   * @param user The User object containing registration details, such as username, password, and
   *             other relevant information.
   * @return A String representing an authentication token for the user. This token should be used
   * to authenticate the user for future operations.
   */
  String registerUser(User user);
}
