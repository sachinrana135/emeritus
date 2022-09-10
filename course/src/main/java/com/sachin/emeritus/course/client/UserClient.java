package com.sachin.emeritus.course.client;

import com.sachin.emeritus.course.config.FeignConfig;
import com.sachin.emeritus.course.dto.GetUsersByIdRequestDto;
import com.sachin.emeritus.course.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "USER-SERVICE", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping(value = "/user/user/{id}", produces = "application/json")
    UserDto getUser(@PathVariable("id") final String id);

    @PostMapping(value = "/user/user/findByIds", produces = "application/json")
    List<UserDto> getUsers(@RequestBody GetUsersByIdRequestDto getUsersByIdRequestDto);

}
