package com.sachin.emeritus.course.service.impl;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import com.sachin.emeritus.course.entity.Course;
import com.sachin.emeritus.course.repository.CourseRepository;
import com.sachin.emeritus.course.service.CourseService;
import com.sachin.emeritus.course.vo.PageAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Course> findAll(Boolean myCourse, PageAndSort pageAndSort) {
        String userId = JwtUtil.getUserId();
        if (null != myCourse && myCourse) {
            return courseRepository.findByCreatedBy(userId, pageAndSort.toPageable());
        } else {
            return courseRepository.findAll(pageAndSort.toPageable());
        }
    }

    @Override
    public Course findById(String id) {
        String userId = JwtUtil.getUserId();
        return courseRepository.findByIdAndCreatedBy(id, userId).orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(String id, Course course) {
        Course existedCourse = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));
        if (!existedCourse.isSameUser(JwtUtil.getUserId())) throw new RuntimeException("Unauthorized access");
        existedCourse.setTitle(course.getTitle());
        existedCourse.setDescription(course.getDescription());
        return courseRepository.save(existedCourse);
    }

    @Override
    public void deleteById(String id) {
        Course course = courseRepository.findByIdAndCreatedBy(id, JwtUtil.getUserId()).orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));
        courseRepository.deleteById(id);
    }

}