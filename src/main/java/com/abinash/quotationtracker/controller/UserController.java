package com.abinash.quotationtracker.controller;

import com.abinash.quotationtracker.dto.request.UserRegisterRequest;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return userService.registerUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
    }
}
