package com.upc.aventurape.platform.publication.domain.services;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.queries.GetPublicationByIdQuery;

import java.util.Optional;

public interface PublicationQueryService {

    Optional<Publication> handle(GetPublicationByIdQuery query);

}
