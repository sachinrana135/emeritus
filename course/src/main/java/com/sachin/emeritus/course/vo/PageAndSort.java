package com.sachin.emeritus.course.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Data
public class PageAndSort {

    private Integer page = 0;
    private Integer size = 20;
    private String orderBy = "desc";

    public Pageable toPageable() {
        final Sort sort = Sort.by(
                orderBy.equalsIgnoreCase("asc") ? ASC : DESC,
                "creationDate"
        );
        return PageRequest.of(page, size, sort);

    }

}
