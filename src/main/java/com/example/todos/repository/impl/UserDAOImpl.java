package com.example.todos.repository.impl;

import com.example.todos.model.User;
import com.example.todos.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

  private static final String CHECK_EMAIL_QUERY = "SELECT COUNT(*) FROM users WHERE email = ?";
  private static final String CHECK_USERNAME_QUERY = "SELECT COUNT(*) FROM users WHERE username = ?";
  private static final String INSERT_QUERY = "INSERT INTO users (email, username, password) VALUES (?, ? ,?)";

  private final JdbcTemplate jdbcTemplate;
  private final PasswordEncoder passwordEncoder;


  @Override
  public boolean validateUniqueEmail(String email) {
    return validateUniqueField(email, CHECK_EMAIL_QUERY);
  }

  @Override
  public boolean validateUniqueUsername(String username) {
    return validateUniqueField(username, CHECK_USERNAME_QUERY);
  }

  @Override
  public void saveUser(User user) {
    jdbcTemplate.update(INSERT_QUERY, new Object[]{user.getEmail(), user.getUsername(), passwordEncoder.encode(user.getPassword())});
  }

  private boolean validateUniqueField(String value, String query){
    Integer count = jdbcTemplate.queryForObject(query, new Object[]{value}, Integer.class);
    return count != null && count == 0;
  }
}
