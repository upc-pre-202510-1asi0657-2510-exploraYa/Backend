package com.upc.aventurape.platform.publication.application.internal.queryservices;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.queries.GetPublicationByIdQuery;
import com.upc.aventurape.platform.publication.domain.services.PublicationQueryService;
import com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationQueryServiceImpl implements PublicationQueryService {

    private final PublicationRepository publicationRepository;

    public PublicationQueryServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Optional<Publication> handle(GetPublicationByIdQuery query) {
        return publicationRepository.findById(Long.valueOf(query.publicationId()));
    }
}
