package com.example.todos.repository.impl;

import com.example.todos.model.User;
import com.example.todos.repository.UserDAO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

  private static final String CHECK_EMAIL_QUERY = "SELECT COUNT(*) FROM users WHERE email = ?";
  private static final String CHECK_USERNAME_QUERY = "SELECT COUNT(*) FROM users WHERE username = ?";
  private static final String INSERT_QUERY = "INSERT INTO users (email, username, password) VALUES (?, ? ,?)";
  private static final String LOAD_BY_USERNAME_QUERY = "SELECT username, password FROM users WHERE username = ?";

  private final JdbcTemplate jdbcTemplate;


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
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    jdbcTemplate.update(INSERT_QUERY, user.getEmail(), user.getUsername(),
        passwordEncoder.encode(user.getPassword()));
  }

  @Override
  public Optional<User> loadByUsername(String username) {
    User user = jdbcTemplate.queryForObject(LOAD_BY_USERNAME_QUERY, new Object[]{username},
        (rs, rowNum) ->
            User.builder()
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build());
    return Optional.ofNullable(user);
  }

  private boolean validateUniqueField(String value, String query) {
    Integer count = jdbcTemplate.queryForObject(query, new Object[]{value}, Integer.class);
    return count != null && count == 0;
  }
}
