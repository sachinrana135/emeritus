package com.sachin.emeritus.course.service.impl;

import com.sachin.emeritus.course.client.UserClient;
import com.sachin.emeritus.course.dto.CourseByUserDto;
import com.sachin.emeritus.course.dto.GetUsersByIdRequestDto;
import com.sachin.emeritus.course.dto.UserByCourseDto;
import com.sachin.emeritus.course.dto.UserDto;
import com.sachin.emeritus.course.entity.Course;
import com.sachin.emeritus.course.entity.CourseUser;
import com.sachin.emeritus.course.repository.CourseRepository;
import com.sachin.emeritus.course.repository.CourseUserRepository;
import com.sachin.emeritus.course.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private CourseUserRepository courseUserRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public CourseUser enrollUser(CourseUser courseUser) {
        courseRepository.findById(courseUser.getCourseId()).orElseThrow(() -> new RuntimeException("Course with id " + courseUser.getCourseId() + " not found"));
        return courseUserRepository.save(courseUser);
    }

    @Override
    public UserByCourseDto getUserByCourse(String courseId) {
        List<CourseUser> courseUserList = courseUserRepository.findByCourseId(courseId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        List<UserDto> users = List.of();
        if (!courseUserList.isEmpty()) {
            List<String> userIds = courseUserList.stream().map(courseUser -> courseUser.getUserId()).collect(Collectors.toList());
            users = userClient.getUsers(new GetUsersByIdRequestDto(userIds));
        }
        return UserByCourseDto.builder().id(course.getId()).title(course.getTitle()).description(course.getDescription()).users(users).build();
    }

    @Override
    public CourseByUserDto getCourseByUser(String userId) {
        List<CourseUser> courseUserList = courseUserRepository.findByUserId(userId);
        UserDto user = userClient.getUser(userId);
        List<Course> courses = List.of();
        if (!courseUserList.isEmpty()) {
            List<String> courseIds = courseUserList.stream().map(courseUser -> courseUser.getCourseId()).collect(Collectors.toList());
            courses = courseRepository.findAllById(courseIds);
        }
        return CourseByUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .dob(user.getDob())
                .courses(courses).build();
    }

}