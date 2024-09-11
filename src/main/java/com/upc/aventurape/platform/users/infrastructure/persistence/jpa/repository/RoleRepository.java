package com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository;

import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName(Roles name);
}
