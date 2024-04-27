package com.emudhra.kms.repository;

import com.emudhra.kms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName(String roleName);
}
