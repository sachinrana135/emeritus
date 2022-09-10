package com.sachin.emeritus.user.enums;

public enum Role {
    SYS_ADMIN,
    INSTRUCTOR,
    STUDENT;

    public static Role roleType(final String roleType){
        try{
            return Role.valueOf(roleType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e){
            throw new RuntimeException("Invalid role Type");
        }
    }
}
