package com.upc.aventurape.platform.users.interfaces.rest.transform;



import com.upc.aventurape.platform.users.domain.model.commands.SignUpCommand;
import com.upc.aventurape.platform.users.domain.model.entities.Role;
import com.upc.aventurape.platform.users.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() !=null
                ? resource.roles().stream().map(Role::toRoleFromName).toList()
                : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.password(), roles);
    }
}
