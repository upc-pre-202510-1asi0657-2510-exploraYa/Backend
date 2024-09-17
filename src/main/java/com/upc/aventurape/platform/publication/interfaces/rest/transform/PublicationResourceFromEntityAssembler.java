package com.upc.aventurape.platform.publication.interfaces.rest.transform;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.PublicationResource;

public class PublicationResourceFromEntityAssembler {


    public static PublicationResource toResourceFromEntity(Publication entity) {
        return new PublicationResource(
               entity.getId(),
                entity.getNameActivity(),
                entity.getDescription(),
                entity.getTimeDuration(),
                entity.getImage(),
                entity.getCantPeople(),
                entity.getCost()
        );
    }



}
