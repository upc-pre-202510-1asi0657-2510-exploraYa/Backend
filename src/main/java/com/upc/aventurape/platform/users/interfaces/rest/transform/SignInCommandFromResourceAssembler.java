package com.upc.aventurape.platform.users.interfaces.rest.transform;


import com.upc.aventurape.platform.users.domain.model.commands.SignInCommand;
import com.upc.aventurape.platform.users.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
