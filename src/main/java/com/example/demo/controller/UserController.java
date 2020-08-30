package com.example.demo.controller;

import com.example.demo.entitiy.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public User join(@RequestBody User user) {
        return userService.insertUser(user);
    }
}
