package com.abinash.quotationtracker.config;

import com.abinash.quotationtracker.entity.Role;
import com.abinash.quotationtracker.enums.RoleType;
import com.abinash.quotationtracker.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        for (RoleType type : RoleType.values()) {
            roleRepository.findByRoleType(type)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoleType(type);
                        return roleRepository.save(role);
                    });
        }
    }
}
