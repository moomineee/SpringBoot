package com.springboot.hello.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // 이렇게 해주면 getter와 생성자 추가를 안해도 된다. 단, lombok을 dependecy에 추가해야함.
@Getter
public class UserRequestDto {

private String id;
private String name;
private String password;

}
