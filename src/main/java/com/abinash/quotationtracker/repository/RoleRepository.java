package com.abinash.quotationtracker.repository;

import com.abinash.quotationtracker.entity.Role;
import com.abinash.quotationtracker.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleType roleType);
}
