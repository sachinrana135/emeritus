package com.sachin.emeritus.course.repository;


import com.sachin.emeritus.course.entity.AssignmentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUser, String>, JpaSpecificationExecutor<AssignmentUser> {

    List<AssignmentUser> findByAssignmentId(String assignmentId);

    List<AssignmentUser> findByUserId(String userId);
}
