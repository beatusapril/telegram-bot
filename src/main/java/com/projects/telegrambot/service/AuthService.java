package com.projects.telegrambot.service;

import com.projects.telegrambot.domain.User;
import com.projects.telegrambot.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.projects.telegrambot.security.JwtUtils;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String auth(User request) {
        User user = userRepository.getByLogin(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Not found user!"));
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password!");
        }
        return JwtUtils.generateToken(user);
    }
}
