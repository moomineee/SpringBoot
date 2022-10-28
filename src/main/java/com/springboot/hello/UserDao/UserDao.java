package com.springboot.hello.UserDao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate; // springboot가 Factory를 구성해 DI를 한다.

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    private RowMapper<User> rowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            return user;
        }
    };

    public void add(User user) {
        this.jdbcTemplate.update("insert into users(id,name,password) values (?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) {
가        String sql = "select id, name, password from users where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }
}
