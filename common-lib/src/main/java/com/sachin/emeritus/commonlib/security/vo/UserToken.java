package com.sachin.emeritus.commonlib.security.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

    private String id;
    private String email;
    private String role;
}
