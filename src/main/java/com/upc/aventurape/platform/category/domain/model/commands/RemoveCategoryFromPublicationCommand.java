package com.upc.aventurape.platform.category.domain.model.commands;

/**
 * Command to remove a category from a publication
 */
public record RemoveCategoryFromPublicationCommand(
    Long publicationId,
    Long categoryId
) {
} 