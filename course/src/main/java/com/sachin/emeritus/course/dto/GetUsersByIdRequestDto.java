package com.sachin.emeritus.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersByIdRequestDto implements Serializable {
    public List<String> userIds;
}
