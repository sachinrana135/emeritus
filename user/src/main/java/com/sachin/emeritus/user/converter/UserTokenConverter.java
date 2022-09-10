package com.sachin.emeritus.user.converter;

import com.sachin.emeritus.commonlib.security.vo.UserToken;
import com.sachin.emeritus.user.entity.User;


public class UserTokenConverter {

    public static UserToken toUserToken(User user) {
        return UserToken.builder()
                .id(user.getId())
                .role(user.getRole().name())
                .email(user.getEmail())
                .build();
    }
}
