package com.upc.aventurape.platform.publication.domain.model.commands;

public record CreatePublicationCommand(
        String  nameActivity,
        String  description,
        Integer  timeDuration,
        String image,
        Integer cantPeople,
        Integer cost
) {
}
