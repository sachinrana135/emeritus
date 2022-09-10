package com.sachin.emeritus.user.service;

import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.vo.PageAndSort;
import com.sachin.emeritus.user.vo.UserFilter;
import com.sachin.emeritus.user.vo.UsernamePassword;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<User> findAll(UserFilter filter, PageAndSort pageAndSort);
    User findById(String id);
    String authenticate(UsernamePassword usernamePassword);
    User save(User user);
    User update(String id, User user);
    void deleteById(String id);
    List<User> findAllById(List<String> userIds);
}
