package com.abinash.quotationtracker.controller;

import com.abinash.quotationtracker.dto.request.LoginRequest;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.repository.UserRepository;
import com.abinash.quotationtracker.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest request) {

        // 1. Authenticate email + password using Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Fetch the authenticated user from database
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generate JWT with REAL userId and role
        String token = jwtUtil.generateToken(
                user.getId(),
                user.getRole().getRoleType().name()
        );

        // 4. Return token to client
        return Map.of("token", token);
    }
}
