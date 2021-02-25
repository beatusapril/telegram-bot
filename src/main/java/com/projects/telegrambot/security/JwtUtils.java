package com.projects.telegrambot.security;

import com.projects.telegrambot.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtils {

    private static final String JWT_SECRET = "W1gdyEsbshsy8@qyuw";

    public static User parseToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        String login = jwsClaims.getBody()
                .get("id", String.class);
        Integer userId = jwsClaims.getBody()
                .get("id", Integer.class);

        return new User(userId, login);
    }

    public static String generateToken(User user) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim("id", user.getId())
                .claim("login", user.getLogin())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();

        return "Bearer " + compactTokenString;
    }
}
