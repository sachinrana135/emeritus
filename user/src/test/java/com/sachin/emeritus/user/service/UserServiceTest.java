package com.sachin.emeritus.user.service;

import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.enums.Role;
import com.sachin.emeritus.user.repository.UserRepository;
import com.sachin.emeritus.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void shouldSaveUser() {

        User user = User.builder().firstName("John")
                .email("john@gmail.com")
                .password("test")
                .role(Role.STUDENT)
                .build();

        User savedUser = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName("John")
                .email("john@gmail.com")
                .password("test")
                .role(Role.STUDENT)
                .build();

        Mockito.when(userRepository.save(user)).thenReturn(savedUser);

        User userEntity = userService.save(user);
        Assertions.assertEquals(user.getEmail(), userEntity.getEmail());

    }
}
