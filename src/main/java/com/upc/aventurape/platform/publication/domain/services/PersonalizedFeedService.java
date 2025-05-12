package com.upc.aventurape.platform.publication.domain.services;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Servicio para la generación de feeds personalizados basados en suscripciones
 */
public interface PersonalizedFeedService {
    
    /**
     * Obtiene un feed personalizado de publicaciones basado en las categorías a las que está suscrito un usuario
     * @param userId ID del usuario
     * @param page número de página (comenzando desde 0)
     * @param size tamaño de página
     * @return página con publicaciones personalizadas
     */
    Page<Publication> getPersonalizedFeed(Long userId, int page, int size);
    
    /**
     * Obtiene un feed de publicaciones populares cuando el usuario no tiene suscripciones
     * @param page número de página (comenzando desde 0)
     * @param size tamaño de página
     * @return página con publicaciones populares
     */
    Page<Publication> getPopularPublications(int page, int size);
} 