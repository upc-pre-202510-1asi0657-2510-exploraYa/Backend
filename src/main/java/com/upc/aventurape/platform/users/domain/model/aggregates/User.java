package com.upc.aventurape.platform.users.domain.model.aggregates;

import com.upc.aventurape.platform.shared.domain.model.agregates.AuditableAbstractAggregateRoot;
import com.upc.aventurape.platform.users.domain.model.entities.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Getter
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(unique = true)
    private String username;

    @Getter
    @NotBlank
    @Size(min = 1, max = 120)
    private String password;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() { this.roles = new HashSet<>();  }
    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }
    public User addRoles(List<Role> roles) {
        var validateRoles = Role.validateRoleSet(roles);
        this.roles.addAll(validateRoles);
        return this;
    }
}
