package com.example.todos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            // Permit all requests to the /auth/** endpoints (e.g., login, registration)
            .requestMatchers("/auth/**").permitAll()
            // All other endpoints should be authenticated
            .anyRequest().authenticated()
        )
        // Enable HTTP Basic authentication
        .httpBasic(Customizer.withDefaults())
        // Disable CSRF for simplicity (useful for APIs, but should be enabled if needed)
        .csrf().disable();

    return http.build();
  }
}
