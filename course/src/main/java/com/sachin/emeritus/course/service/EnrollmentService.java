package com.sachin.emeritus.course.service;


import com.sachin.emeritus.course.dto.CourseByUserDto;
import com.sachin.emeritus.course.dto.UserByCourseDto;
import com.sachin.emeritus.course.entity.CourseUser;

public interface EnrollmentService {

    CourseUser enrollUser(CourseUser courseUser);

    UserByCourseDto getUserByCourse(String courseId);

    CourseByUserDto getCourseByUser(String userId);

}
