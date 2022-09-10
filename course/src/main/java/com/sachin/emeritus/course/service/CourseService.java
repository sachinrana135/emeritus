package com.sachin.emeritus.course.service;


import com.sachin.emeritus.course.entity.Course;
import com.sachin.emeritus.course.vo.PageAndSort;
import org.springframework.data.domain.Page;

public interface CourseService {

    Page<Course> findAll(Boolean myCourse, PageAndSort pageAndSort);
    Course findById(String id);
    Course save(Course course);
    Course update(String id, Course course);
    void deleteById(String id);

}
