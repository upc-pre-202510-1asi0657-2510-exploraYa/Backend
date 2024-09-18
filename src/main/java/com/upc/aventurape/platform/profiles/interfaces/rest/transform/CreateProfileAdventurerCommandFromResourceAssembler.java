package com.upc.aventurape.platform.profiles.interfaces.rest.transform;

import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileAdventurerCommand;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.CreateProfileAdventurerResource;

public class CreateProfileAdventurerCommandFromResourceAssembler{
    public static CreateProfileAdventurerCommand toCommandFromResource(CreateProfileAdventurerResource resource){
        return new CreateProfileAdventurerCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country(),
                resource.gender()
        );
    }
}
