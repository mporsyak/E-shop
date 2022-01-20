package com.example.spring_eshop.service;

import com.example.spring_eshop.domain.User;
import com.example.spring_eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
    void save(User user);
    List<UserDto> getAll();

    User findByName(String name);
    void updateProfile(UserDto userDto);
}
