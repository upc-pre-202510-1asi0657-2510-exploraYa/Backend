// src/main/java/com/upc/aventurape/platform/publication/interfaces/rest/FavoritePublicationController.java
package com.upc.aventurape.platform.publication.interfaces.rest;

import com.upc.aventurape.platform.iam.infrastructure.security.SecurityUtils;
import com.upc.aventurape.platform.publication.domain.model.entities.Favorite;
import com.upc.aventurape.platform.publication.domain.model.queries.GetAllFavoritePublicationsQuery;
import com.upc.aventurape.platform.publication.domain.model.queries.GetAllPublicationsQuery;
import com.upc.aventurape.platform.publication.domain.services.FavoritePublicationCommandService;
import com.upc.aventurape.platform.publication.domain.services.FavoritePublicationQueryService;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.FavoriteResource;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.CreateFavoritePublicationResource;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.PublicationResource;
import com.upc.aventurape.platform.publication.interfaces.rest.transform.FavoritePublicationResourceFromEntityAssembler;
import com.upc.aventurape.platform.publication.interfaces.rest.transform.CreateFavoritePublicationCommandFromResourceAssembler;
import com.upc.aventurape.platform.publication.interfaces.rest.transform.PublicationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value= "/api/v1/favorite-publications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Favorite Publications")
public class FavoritePublicationController {

    private final FavoritePublicationCommandService favoriteCommandService;
    private final FavoritePublicationQueryService favoriteQueryService;

    public FavoritePublicationController(FavoritePublicationCommandService favoriteCommandService, FavoritePublicationQueryService favoriteQueryService) {
        this.favoriteCommandService = favoriteCommandService;
        this.favoriteQueryService = favoriteQueryService;
    }

    @PostMapping("/create-favorite-publication")
    public ResponseEntity<FavoriteResource> createFavoritePublication(@RequestBody CreateFavoritePublicationResource resource) {
        Long entrepreneurId = SecurityUtils.getCurrentUserId();
        var createFavoritePublicationCommand = CreateFavoritePublicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var favorite = favoriteCommandService.handle(createFavoritePublicationCommand);
        if (favorite == null) {
            return ResponseEntity.badRequest().build();
        }
        var favoriteResource = FavoritePublicationResourceFromEntityAssembler.
                toResourceFromEntity(favorite);
        return new ResponseEntity<>(favoriteResource, HttpStatus.CREATED);
    }

    @GetMapping("/all-favorites-publications")
    public ResponseEntity<List<FavoriteResource>> getAll() {
        var getAllFavoritePublicationsQuery = new GetAllFavoritePublicationsQuery();
        var favoritePublications = favoriteQueryService.handle(getAllFavoritePublicationsQuery);
        var favoritePublicationsResources = favoritePublications.stream().map(FavoritePublicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(favoritePublicationsResources, HttpStatus.OK);
    }
}