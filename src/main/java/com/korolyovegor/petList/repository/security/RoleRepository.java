package com.korolyovegor.petList.repository.security;

import com.korolyovegor.petList.model.security.Role;
import com.korolyovegor.petList.model.security.RoleNameType;
import com.korolyovegor.petList.model.security.RoleStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleNameType name);
}
