package com.springboot.hello.controller;

import com.springboot.hello.UserDao.UserDao;
import com.springboot.hello.domain.User;
import com.springboot.hello.domain.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        try {
            User user = userDao.findById(id);
            return ResponseEntity.ok()
                    .body(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    public ResponseEntity<User> get(@PathVariable String id) { // 위의 get은 이걸 try-catch한 것.
//        User user = userDao.findById(id);
//        return ResponseEntity.ok()
//                .body(this.userDao.findById(id));
//    }

    //postMapping 추가해야함.
    @PostMapping("/")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
        return ResponseEntity
                .ok()
                .body(userDao.add(user));
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() { // deleteAll로 매핑하므로 RequestBody는 필요가 없다.
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }
}
