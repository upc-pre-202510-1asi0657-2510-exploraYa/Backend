package com.upc.aventurape.platform.category.domain.model.queries;

/**
 * Query to get publications assigned to a category
 */
public record GetPublicationsByCategoryIdQuery(
    Long categoryId
) {
} 