package com.upc.aventurape.platform.publication.domain.model.commands;

import com.upc.aventurape.platform.publication.domain.model.entities.Adventure;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.EntrepreneurId;

public record UpdatePublicationCommand(
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
