package com.projects.telegrambot.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.projects.telegrambot.security.JwtUtils;

@RestController
public class HelloEndpoint {

    @GetMapping("/hello")
    public String greeting(@RequestHeader("Authorization") String token) {
        return String.format("Hello %s", JwtUtils.parseToken(token).getLogin());
    }
}
