package com.sachin.emeritus.course.controller;

import com.sachin.emeritus.course.entity.Course;
import com.sachin.emeritus.course.service.CourseService;
import com.sachin.emeritus.course.vo.PageAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(Boolean myCourse, PageAndSort pageAndSort) {
        return ResponseEntity.ok(courseService.findAll(myCourse, pageAndSort));
    }

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    ResponseEntity<String> createCourse(@RequestBody Course course) {
        Course savedEntity = courseService.save(course);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedEntity.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    ResponseEntity<Void> updateCourse(@PathVariable String id, @RequestBody Course course) {
        Course updatedEntity = courseService.update(id, course);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    void deleteEmployee(@PathVariable String id) {
        courseService.deleteById(id);
    }


}
