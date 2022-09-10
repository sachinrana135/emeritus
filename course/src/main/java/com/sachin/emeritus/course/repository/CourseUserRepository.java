package com.sachin.emeritus.course.repository;


import com.sachin.emeritus.course.entity.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, String>, JpaSpecificationExecutor<CourseUser> {

    List<CourseUser> findByCourseId(String courseId);

    List<CourseUser> findByUserId(String userId);

    Optional<CourseUser> findByUserIdAndCourseId(String userId, String courseId);
}
