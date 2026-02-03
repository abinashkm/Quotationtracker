package com.abinash.quotationtracker.service.impl;

import com.abinash.quotationtracker.entity.Role;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.enums.RoleType;
import com.abinash.quotationtracker.repository.RoleRepository;
import com.abinash.quotationtracker.repository.UserRepository;
import com.abinash.quotationtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(String name, String email, String password, String role) {

        RoleType roleType = RoleType.valueOf(role.toUpperCase());

        Role userRole = roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // plaintext for now (JWT later)
        user.setRole(userRole);

        return userRepository.save(user);
    }
}
