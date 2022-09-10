package com.sachin.emeritus.course.controller;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import com.sachin.emeritus.course.dto.CourseByUserDto;
import com.sachin.emeritus.course.dto.UserByCourseDto;
import com.sachin.emeritus.course.entity.CourseUser;
import com.sachin.emeritus.course.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CourseUser> enrollUser(@RequestBody CourseUser courseUser) {
        return ResponseEntity.ok(enrollmentService.enrollUser(courseUser));
    }

    @GetMapping(value =  "/user/{id}/course")
    public ResponseEntity<CourseByUserDto> getCoursesByUser(@PathVariable("id") final String userId ) {
        return ResponseEntity.ok(enrollmentService.getCourseByUser(userId));
    }

    @PreAuthorize("hasAnyRole('SYS_ADMIN','INSTRUCTOR')")
    @GetMapping(value =  "/course/{id}/user")
    public ResponseEntity<UserByCourseDto> getUserByCourse(@PathVariable("id") final String courseId) {
        return ResponseEntity.ok(enrollmentService.getUserByCourse(courseId));
    }

    @GetMapping(value =  "/user/course")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CourseByUserDto> getMyCourse() {
        return ResponseEntity.ok(enrollmentService.getCourseByUser(JwtUtil.getUserId()));
    }
}
