package com.projects.telegrambot.repository;

import com.projects.telegrambot.domain.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public UserRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<User> getByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = :login";
        return Optional.ofNullable(jdbc.query(sql, Collections.singletonMap("login", login), this::extract));
    }

    private User extract(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId((Integer) rs.getObject("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
