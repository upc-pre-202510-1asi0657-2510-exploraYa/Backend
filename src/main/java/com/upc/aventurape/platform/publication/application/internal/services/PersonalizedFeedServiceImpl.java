package com.upc.aventurape.platform.publication.application.internal.services;

import com.upc.aventurape.platform.category.interfaces.acl.CategoryContextFacade;
import com.upc.aventurape.platform.category.interfaces.acl.transform.CategoryDto;
import com.upc.aventurape.platform.category.interfaces.acl.transform.PublicationIdDto;
import com.upc.aventurape.platform.publication.application.internal.outboundservices.acl.ExternalCategoryService;
import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.services.PersonalizedFeedService;
import com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de feed personalizado
 */
@Service
public class PersonalizedFeedServiceImpl implements PersonalizedFeedService {

    private final PublicationRepository publicationRepository;
    private final ExternalCategoryService externalCategoryService;

    public PersonalizedFeedServiceImpl(PublicationRepository publicationRepository,
                                      ExternalCategoryService externalCategoryService) {
        this.publicationRepository = publicationRepository;
        this.externalCategoryService = externalCategoryService;
    }

    @Override
    public Page<Publication> getPersonalizedFeed(Long userId, int page, int size) {
        // Obtener categorías suscritas por el usuario
        List<CategoryDto> subscribedCategories = externalCategoryService.getSubscribedCategoriesByUserId(userId);
        
        if (subscribedCategories.isEmpty()) {
            // Si no hay suscripciones, mostrar publicaciones populares
            return getPopularPublications(page, size);
        }

        // Extraer IDs de categorías
        List<Long> categoryIds = subscribedCategories.stream()
                .map(CategoryDto::getId)
                .collect(Collectors.toList());
        
        // Para cada categoría, obtener IDs de publicaciones
        List<Long> publicationIds = categoryIds.stream()
                .map(externalCategoryService::getPublicationIdsByCategoryId)
                .flatMap(List::stream)
                .distinct() // Eliminar duplicados
                .collect(Collectors.toList());
        
        if (publicationIds.isEmpty()) {
            // Si no hay publicaciones para estas categorías, mostrar publicaciones populares
            return getPopularPublications(page, size);
        }
        
        // Crear paginación con ordenamiento por fecha de creación descendente
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        
        // Buscar publicaciones por IDs
        return publicationRepository.findByIdIn(publicationIds, pageable);
    }

    @Override
    public Page<Publication> getPopularPublications(int page, int size) {
        // Crear paginación con ordenamiento por rating descendente
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rating"));
        
        // Buscar publicaciones ordenadas por popularidad (rating)
        return publicationRepository.findAll(pageable);
    }
} 