package com.upc.aventurape.platform.publication.interfaces.rest.resources;

import com.upc.aventurape.platform.publication.domain.model.entities.Adventure;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.EntrepreneurId;

public record UpdatePublicationResource(
        Long publicationId,
        EntrepreneurId entrepreneurId,
        Adventure adventure,
        String  nameActivity,
        String  description,
        Integer  timeDuration,
        String image,
        Integer cantPeople,
        Integer cost
) {

}
