package com.upc.aventurape.platform.profiles.interfaces.rest.transform;

import com.upc.aventurape.platform.profiles.domain.model.aggregates.ProfileEntrepreneur;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.ProfileEntrepreneurResource;

public class ProfileEntrepreneurResourceFromEntityAssembler {
    public static ProfileEntrepreneurResource toResourceFromEntity(ProfileEntrepreneur entity) {
        return new ProfileEntrepreneurResource(
                entity.getId(),
                entity.getName(),
                entity.getEmailAddress(),
                entity.getStreetAddress()
        );
    }
}
