package com.example.todos.security;

import com.example.todos.model.User;
import com.example.todos.repository.UserDAO;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcUserDetailsService implements UserDetailsService {

  private final UserDAO userDAO;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDAO.loadByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("user not found."));
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), true, true, true, true,
        Collections.emptyList());
  }
}
