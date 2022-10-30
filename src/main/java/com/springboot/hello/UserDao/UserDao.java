package com.springboot.hello.UserDao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component // 스프링이 관리하는 빈으로 등록. 빈으로 등록된 객체는 다른 클래스가 인터페이스를 가지고 의존성을 주입받을 때 이 구현체를 찾아 주입하게 된다.
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate; // springboot가 Factory를 구성해 DI를 한다.

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    } // DAO 객체에서도 DB에 접근하기 위해 리포지토리  인터페이스를 사용해 의존성 주입을 받아야 한다.
      // 리포지토리를 정의하고 생성자를 통해 의존성을 주입받는 과정.

    private RowMapper<User> rowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            return user;
        }
    };

    public int add(User user) {
        return this.jdbcTemplate.update("insert into users(id,name,password) values (?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) { // 내부적으로 EntitiyManger의 find()메서드 호출. 리턴 값으로 String 객체 전달.
        String sql = "select id, name, password from users where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }
}
