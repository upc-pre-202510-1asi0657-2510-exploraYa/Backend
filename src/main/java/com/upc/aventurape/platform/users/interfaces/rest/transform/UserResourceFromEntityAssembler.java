package com.upc.aventurape.platform.users.interfaces.rest.transform;

import com.upc.aventurape.platform.users.domain.model.aggregates.User;
import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), roles);
    }
}
