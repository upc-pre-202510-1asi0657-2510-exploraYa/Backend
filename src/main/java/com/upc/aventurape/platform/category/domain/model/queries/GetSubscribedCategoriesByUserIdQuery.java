package com.upc.aventurape.platform.category.domain.model.queries;

/**
 * Query to get categories subscribed by a user
 */
public record GetSubscribedCategoriesByUserIdQuery(
    Long userId
) {
} 