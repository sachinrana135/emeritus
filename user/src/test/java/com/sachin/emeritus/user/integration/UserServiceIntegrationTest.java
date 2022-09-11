package com.sachin.emeritus.user.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.emeritus.commonlib.security.JwtUtil;
import com.sachin.emeritus.commonlib.security.vo.UserToken;
import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.enums.Gender;
import com.sachin.emeritus.user.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSaveUser() throws JsonProcessingException {

        User user = User.builder().firstName("Pop")
                .email("pop@gmail.com")
                .password("test")
                .lastName("Wick")
                .gender(Gender.MALE)
                .dob("25-09-1992")
                .role(Role.STUDENT)
                .build();

        String requestBody = objectMapper.writeValueAsString(user);

        String token = JwtUtil.generateToken(UserToken.builder().id(UUID.randomUUID().toString()).email("Jessica@gmail.com").role("SYS_ADMIN").build());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(requestBody, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity("/user", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(false, response.getBody().isEmpty());

    }
}
