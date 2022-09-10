package com.sachin.emeritus.course.repository;


import com.sachin.emeritus.course.entity.Assignment;
import com.sachin.emeritus.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String>, JpaSpecificationExecutor<Course> {

}
