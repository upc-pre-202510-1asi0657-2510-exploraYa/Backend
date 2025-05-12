package com.upc.aventurape.platform.category.domain.model.queries;

/**
 * Query to get popular categories based on subscriber count
 */
public record GetPopularCategoriesQuery(
    Integer limit
) {
    public GetPopularCategoriesQuery() {
        this(10); // Default limit to 10 categories
    }
} 