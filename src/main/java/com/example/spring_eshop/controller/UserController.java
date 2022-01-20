package com.example.spring_eshop.controller;

import com.example.spring_eshop.domain.User;
import com.example.spring_eshop.dto.UserDto;
import com.example.spring_eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("user", userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDto userDto, Model model){
        if (userService.save(userDto)){
            return "redirect:/users";
        }else {
            model.addAttribute("user", userDto);
            return "user";
        }
    }
    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if (principal == null){
            throw new RuntimeException("You are not logged in");
        }
        User user = userService.findByName(principal.getName());

        UserDto userDto =UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDto userDto, Model model, Principal principal){
        if (principal == null || !Objects.equals(principal.getName(), userDto.getUsername())){
            throw new RuntimeException("You are not logged in");
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty() && !Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())){
            model.addAttribute("user", userDto);
            return "profile";
        }
        userService.updateProfile(userDto);
        return "rederect:/users?profile";
    }
}
