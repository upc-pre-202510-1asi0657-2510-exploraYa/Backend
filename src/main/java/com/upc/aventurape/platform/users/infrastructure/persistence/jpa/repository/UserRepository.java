package com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository;

import com.upc.aventurape.platform.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
