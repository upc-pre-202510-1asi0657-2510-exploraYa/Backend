package com.upc.aventurape.platform.publication.interfaces.rest;


import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.commands.CreatePublicationCommand;
import com.upc.aventurape.platform.publication.domain.model.queries.GetPublicationByIdQuery;
import com.upc.aventurape.platform.publication.domain.services.PublicationCommandService;
import com.upc.aventurape.platform.publication.domain.services.PublicationQueryService;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.CreatePublicationResource;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.PublicationResource;
import com.upc.aventurape.platform.publication.interfaces.rest.transform.CreatePublicationCommandFromResourceAssembler;
import com.upc.aventurape.platform.publication.interfaces.rest.transform.PublicationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/publication", produces = APPLICATION_JSON_VALUE)
@Tag(name = "AdventuratePe", description = "Publication of AdventuratePe Management Endpoints")

public class PublicationController {

    private final PublicationCommandService publicationCommandService;
    private final PublicationQueryService publicationQueryService;


    public PublicationController(
            PublicationCommandService publicationCommandService,
            PublicationQueryService publicationQueryService
    ) {
        this.publicationCommandService = publicationCommandService;
        this.publicationQueryService = publicationQueryService;
    }



    /**
     * Creates a new publication.
     *
     * @param resource the resource containing the data for the publication to be created
     * @return the created publication resource
     * @see CreatePublicationResource
     * @see PublicationResource
     */

    @PostMapping
    public ResponseEntity<PublicationResource> createPublication(@RequestBody CreatePublicationResource resource) {
        var createPublicationCommand = CreatePublicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var publicationId = publicationCommandService.handle(createPublicationCommand);

        if (publicationId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (publicationId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getPublicationByIdQuery = new GetPublicationByIdQuery(publicationId);
        var publication = publicationQueryService.handle(getPublicationByIdQuery);

        if (publication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(publication.get());
        return new ResponseEntity<>(publicationResource, HttpStatus.CREATED);
    }
}
