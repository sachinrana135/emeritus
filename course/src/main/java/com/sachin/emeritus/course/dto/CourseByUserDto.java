package com.sachin.emeritus.course.dto;

import com.sachin.emeritus.course.entity.Course;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
public class CourseByUserDto extends UserDto implements Serializable {
    List<Course> courses;
}
