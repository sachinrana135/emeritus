package com.sachin.emeritus.course.controller;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import com.sachin.emeritus.course.entity.Assignment;
import com.sachin.emeritus.course.entity.AssignmentUser;
import com.sachin.emeritus.course.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @PostMapping("/assign")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<AssignmentUser> assignAssignmentToStudent(@RequestBody AssignmentUser assignmentUser) {
        return ResponseEntity.ok(assignmentService.assignAssignmentToStudent(assignmentUser));
    }

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignment) {
        Assignment savedAssignment = assignmentService.create(assignment);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAssignment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/{userAssignmentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> submitAssignment(@PathVariable("userAssignmentId") final String userAssignmentId) {
        assignmentService.updateAssignmentStatus(userAssignmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Assignment>> getMyAssignments() {
        return ResponseEntity.ok(assignmentService.findAssignmentByUserId(JwtUtil.getUserId()));
    }
}
