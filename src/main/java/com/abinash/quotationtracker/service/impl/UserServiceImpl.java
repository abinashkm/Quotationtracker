package com.abinash.quotationtracker.service.impl;

import com.abinash.quotationtracker.entity.Role;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.enums.RoleType;
import com.abinash.quotationtracker.repository.RoleRepository;
import com.abinash.quotationtracker.repository.UserRepository;
import com.abinash.quotationtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Required for JWT + Spring Security authentication
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String name, String email, String password, String role) {

        // Convert role string to enum (strict validation)
        RoleType roleType = RoleType.valueOf(role.toUpperCase());

        // Fetch role entity from DB
        Role userRole = roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Create user
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        // IMPORTANT CHANGE:
        // Password must be encoded for Spring Security authentication
        user.setPassword(passwordEncoder.encode(password));

        user.setRole(userRole);

        return userRepository.save(user);
    }
}
