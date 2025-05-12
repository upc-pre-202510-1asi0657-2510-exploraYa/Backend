package com.upc.aventurape.platform.category.domain.model.commands;

/**
 * Command to subscribe a user to a category
 */
public record SubscribeToCategoryCommand(
    Long userId,
    Long categoryId
) {
} 