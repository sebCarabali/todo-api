package com.example.todos.security;

import com.example.todos.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  private JwtUtil() {
  }

  public String encode(User user) {
    Key hmackey = new SecretKeySpec(Base64.getDecoder().decode(secret),
        SignatureAlgorithm.HS256.getJcaName());

    return Jwts.builder()
        .setIssuer("todo-api")
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
        .claim("user", user.getUsername())
        .claim("email", user.getEmail())
        .signWith(hmackey)
        .compact();
  }

  public User decode(String jwtToken) {
    return null;
  }
}
