package com.projects.telegrambot.endpoint;

import com.projects.telegrambot.domain.User;
import com.projects.telegrambot.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthEndpoint {

    private final AuthService authService;

    public AuthEndpoint(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private String auth(@RequestBody User user){
        return authService.auth(user);
    }
}
