package com.upc.aventurape.platform.users.domain.services;

import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.domain.model.queries.GetAllRolesQuery;
import com.upc.aventurape.platform.users.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
