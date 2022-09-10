package com.sachin.emeritus.course.entity;

import com.sachin.emeritus.course.enums.AssignmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Data
@Builder
@Table(name = "assignment_user")
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentUser {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Assignment assignment;

    private String userId;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
}
