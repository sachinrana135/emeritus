package com.sachin.emeritus.course.repository;


import com.sachin.emeritus.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    Page<Course> findByCreatedBy(String userId, Pageable toPageable);

    Optional<Course> findByIdAndCreatedBy(String id, String createdBy);
}
