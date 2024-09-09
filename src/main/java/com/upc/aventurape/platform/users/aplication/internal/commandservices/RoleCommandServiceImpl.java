package com.upc.aventurape.platform.users.aplication.internal.commandservices;

import com.upc.aventurape.platform.users.domain.model.commands.SeedRolesCommand;
import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.domain.model.valueobjects.Roles;
import com.upc.aventurape.platform.users.domain.services.RoleCommandService;
import com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command){
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)){
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
