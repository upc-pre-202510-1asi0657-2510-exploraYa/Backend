package com.upc.aventurape.platform.profiles.interfaces.rest.transform;

import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileEntrepreneurCommand;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.CreateProfileEntrepreneurResource;

public class CreateProfileEntrepreneurCommandFromResourceAssembler {
    public static CreateProfileEntrepreneurCommand toCommandFromResource (CreateProfileEntrepreneurResource resource) {
        return new CreateProfileEntrepreneurCommand(
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country(),
                resource.name()
        );
    }
}
