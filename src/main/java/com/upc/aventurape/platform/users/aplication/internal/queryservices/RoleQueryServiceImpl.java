package com.upc.aventurape.platform.users.aplication.internal.queryservices;

import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.domain.model.queries.GetAllRolesQuery;
import com.upc.aventurape.platform.users.domain.model.queries.GetRoleByNameQuery;
import com.upc.aventurape.platform.users.domain.services.RoleQueryService;
import com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    public final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {this.roleRepository = roleRepository;  }

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
