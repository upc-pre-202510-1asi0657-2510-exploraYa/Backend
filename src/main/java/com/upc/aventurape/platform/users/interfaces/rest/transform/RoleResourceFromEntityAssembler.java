package com.upc.aventurape.platform.users.interfaces.rest.transform;

import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());

    }
}