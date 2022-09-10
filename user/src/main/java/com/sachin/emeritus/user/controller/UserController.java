package com.sachin.emeritus.user.controller;

import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.service.UserService;
import com.sachin.emeritus.user.vo.PageAndSort;
import com.sachin.emeritus.user.vo.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('SYS_ADMIN')")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping

    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Page<User>> getAllUsers(UserFilter filter, PageAndSort pageAndSort) {
        return ResponseEntity.ok(userService.findAll(filter, pageAndSort));
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        User savedEntity = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateEmployee(@PathVariable String id, @RequestBody User user) {
        User updatedEntity = userService.update(id, user);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) {
        userService.deleteById(id);
    }


}
