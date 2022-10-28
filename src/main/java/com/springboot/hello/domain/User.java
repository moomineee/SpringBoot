package com.springboot.hello.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public class User {
    private String id;
    private String name;
    private String password;
//  @AllArgsConstructor와 @Getter Annotation으로 대체 가능.
//    public User(String id, String name, String password) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
}
