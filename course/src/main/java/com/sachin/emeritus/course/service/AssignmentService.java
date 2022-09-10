package com.sachin.emeritus.course.service;


import com.sachin.emeritus.course.entity.Assignment;
import com.sachin.emeritus.course.entity.AssignmentUser;

import java.util.List;

public interface AssignmentService {

    AssignmentUser assignAssignmentToStudent(AssignmentUser assignmentUser);

    List<Assignment> findAssignmentByUserId(String userId);

    Assignment create(Assignment assignment);

    AssignmentUser updateAssignmentStatus(String  userAssignmentId);

}
