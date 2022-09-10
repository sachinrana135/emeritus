package com.sachin.emeritus.user.service.impl;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import com.sachin.emeritus.user.converter.UserTokenConverter;
import com.sachin.emeritus.user.entity.User;
import com.sachin.emeritus.user.repository.UserRepository;
import com.sachin.emeritus.user.service.UserService;
import com.sachin.emeritus.user.vo.PageAndSort;
import com.sachin.emeritus.user.vo.UserFilter;
import com.sachin.emeritus.user.vo.UserSpecification;
import com.sachin.emeritus.user.vo.UsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(UserFilter filter, PageAndSort pageAndSort) {
        Specification<User> specification = new UserSpecification(filter);
        return userRepository.findAll(specification, pageAndSort.toPageable());
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public String authenticate(UsernamePassword usernamePassword) {
        User user = userRepository.findByEmailAndPassword(usernamePassword.getUsername(), usernamePassword.getPassword())
                .orElseThrow(() -> new RuntimeException("email/password is incorrect for user " + usernamePassword.getUsername()));

        return JwtUtil.generateToken(UserTokenConverter.toUserToken(user));
    }

    @Override
    public List<User> findAllById(List<String> userIds) {
        return userRepository.findAllById(userIds);
    }
}