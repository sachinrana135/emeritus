package com.sachin.emeritus.user.controller;

import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.service.UserService;
import com.sachin.emeritus.user.vo.UsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UsernamePassword usernamePassword) {
        return ResponseEntity.ok(userService.authenticate(usernamePassword));
    }
}
