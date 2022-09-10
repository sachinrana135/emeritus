package com.sachin.emeritus.course.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
public class UserByCourseDto implements Serializable {
    private String id;

    private String title;

    private String description;
    private List<UserDto> users;
}
