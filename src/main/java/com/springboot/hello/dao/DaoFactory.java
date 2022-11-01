package com.springboot.hello.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DaoFactory {

    @Bean // 이런식으로 component없이 직접 bean으로 등록하는 방법도 있다.
    HospitalDao hospitalDao() {
        return new HospitalDao(new JdbcTemplate());
    }
}
