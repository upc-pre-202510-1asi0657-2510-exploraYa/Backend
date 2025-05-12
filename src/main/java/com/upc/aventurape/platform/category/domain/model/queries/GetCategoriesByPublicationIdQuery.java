package com.upc.aventurape.platform.category.domain.model.queries;

/**
 * Query to get categories assigned to a publication
 */
public record GetCategoriesByPublicationIdQuery(
    Long publicationId
) {
} 