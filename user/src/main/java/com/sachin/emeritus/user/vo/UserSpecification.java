package com.sachin.emeritus.user.vo;

import com.sachin.emeritus.user.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class UserSpecification implements Specification<User> {

    private final UserFilter filter;

    public UserSpecification(UserFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (nonNull(filter.getRole())) {
            predicates.add(criteriaBuilder.equal(
                    root.get("role").get("userRole"), filter.getRole()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
