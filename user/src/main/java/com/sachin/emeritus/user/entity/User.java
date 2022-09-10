package com.sachin.emeritus.user.entity;
import com.sachin.emeritus.user.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.sachin.emeritus.user.enums.Gender;


@Entity
@Data
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable<String>{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    @Column(unique=true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String dob;

    @Enumerated(EnumType.STRING)
    private Role role;

}
