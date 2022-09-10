package com.sachin.emeritus.user;

import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.enums.Gender;
import com.sachin.emeritus.user.enums.Role;
import com.sachin.emeritus.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    public void run(ApplicationArguments args) {
       User user1 =  User.builder()
                .id(UUID.randomUUID().toString())
                .role(Role.SYS_ADMIN)
                .email("john@gmail.com")
                .firstName("John")
                .lastName("Wick")
                .password("john@123")
                .gender(Gender.MALE)
                .dob("25-09-1991")
                .build();

        User user2 =  User.builder()
                .id(UUID.randomUUID().toString())
                .role(Role.STUDENT)
                .email("jessica@gmail.com")
                .firstName("Jessica")
                .lastName("Adam")
                .password("jessica@123")
                .gender(Gender.FEMALE)
                .dob("25-09-1992")
                .build();

        User user3 =  User.builder()
                .id(UUID.randomUUID().toString())
                .role(Role.INSTRUCTOR)
                .email("chris@gmail.com")
                .firstName("Chris")
                .lastName("Moris")
                .password("chris@123")
                .gender(Gender.MALE)
                .dob("25-09-1992")
                .build();

      userRepository.saveAll(List.of(user1, user2, user3));
    }
}
