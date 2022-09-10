package com.sachin.emeritus.course.service.impl;

import com.sachin.emeritus.course.entity.Assignment;
import com.sachin.emeritus.course.entity.AssignmentUser;
import com.sachin.emeritus.course.enums.AssignmentStatus;
import com.sachin.emeritus.course.repository.AssignmentRepository;
import com.sachin.emeritus.course.repository.AssignmentUserRepository;
import com.sachin.emeritus.course.repository.CourseRepository;
import com.sachin.emeritus.course.repository.CourseUserRepository;
import com.sachin.emeritus.course.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentUserRepository assignmentUserRepository;

    @Autowired
    private CourseUserRepository courseUserRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public AssignmentUser assignAssignmentToStudent(AssignmentUser assignmentUser) {
        courseUserRepository.findByUserIdAndCourseId(assignmentUser.getUserId(), assignmentUser.getAssignment().getCourse().getId()).orElseThrow(() -> new RuntimeException("Student is not enrolled in this course"));
        assignmentUser.setStatus(AssignmentStatus.ASSIGNED);
        return assignmentUserRepository.save(assignmentUser);
    }

    @Override
    public List<Assignment> findAssignmentByUserId(String userId) {
        List<AssignmentUser> assignmentUsers = assignmentUserRepository.findByUserId(userId);
        return assignmentUsers.stream().map(assignmentUser -> assignmentUser.getAssignment()).collect(Collectors.toList());
    }

    @Override
    public Assignment create(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public AssignmentUser updateAssignmentStatus(String userAssignmentId) {
        AssignmentUser existingAssignmentUser = assignmentUserRepository.findById(userAssignmentId).orElseThrow(() -> new RuntimeException("Record not found"));
        existingAssignmentUser.setStatus(AssignmentStatus.COMPLETED);
        return assignmentUserRepository.save(existingAssignmentUser);
    }
}