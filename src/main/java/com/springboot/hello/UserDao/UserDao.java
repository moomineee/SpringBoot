package com.springboot.hello.UserDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate; // springboot가 Factory를 구성해 DI를 한다.

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }
    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }
}
