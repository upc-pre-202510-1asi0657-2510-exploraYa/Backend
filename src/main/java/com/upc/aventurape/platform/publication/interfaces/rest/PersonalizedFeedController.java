package com.upc.aventurape.platform.publication.interfaces.rest;

import com.upc.aventurape.platform.iam.infrastructure.security.SecurityUtils;
import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.services.PersonalizedFeedService;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.PublicationResource;
import com.upc.aventurape.platform.publication.interfaces.rest.transform.PublicationResourceFromEntityAssembler;
// import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para el feed personalizado
 */
@RestController
@RequestMapping(value = "/api/v1/feed", produces = MediaType.APPLICATION_JSON_VALUE)
// @Tag(name = "Personalized Feed")
public class PersonalizedFeedController {
    
    private final PersonalizedFeedService personalizedFeedService;
    
    public PersonalizedFeedController(PersonalizedFeedService personalizedFeedService) {
        this.personalizedFeedService = personalizedFeedService;
    }
    
    /**
     * Obtiene un feed personalizado basado en las suscripciones a categorías del usuario actual
     * @param page número de página (comenzando desde 0)
     * @param size tamaño de página
     * @return página con publicaciones personalizadas
     */
    /*
    @GetMapping("/personalized")
    public ResponseEntity<Page<PublicationResource>> getPersonalizedFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // Obtener el ID del usuario actual del contexto de seguridad
        Long userId = SecurityUtils.getCurrentUserId();
        
        // Obtener el feed personalizado
        Page<Publication> feed = personalizedFeedService.getPersonalizedFeed(userId, page, size);
        
        // Convertir entidades a recursos
        List<PublicationResource> resourceList = feed.getContent().stream()
                .map(PublicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        
        // Crear una nueva página con los recursos
        Page<PublicationResource> resourcePage = new PageImpl<>(
                resourceList, 
                feed.getPageable(),
                feed.getTotalElements());
        
        return ResponseEntity.ok(resourcePage);
    }
    */
    
    /**
     * Obtiene un feed de publicaciones populares
     * @param page número de página (comenzando desde 0) 
     * @param size tamaño de página
     * @return página con publicaciones populares
     */
    /*
    @GetMapping("/popular")
    public ResponseEntity<Page<PublicationResource>> getPopularFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // Obtener el feed de publicaciones populares
        Page<Publication> feed = personalizedFeedService.getPopularPublications(page, size);
        
        // Convertir entidades a recursos
        List<PublicationResource> resourceList = feed.getContent().stream()
                .map(PublicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        
        // Crear una nueva página con los recursos
        Page<PublicationResource> resourcePage = new PageImpl<>(
                resourceList, 
                feed.getPageable(),
                feed.getTotalElements());
        
        return ResponseEntity.ok(resourcePage);
    }
    */
} 