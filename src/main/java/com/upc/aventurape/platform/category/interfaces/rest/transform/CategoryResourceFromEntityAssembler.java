package com.upc.aventurape.platform.category.interfaces.rest.transform;

import com.upc.aventurape.platform.category.domain.model.aggregates.Category;
import com.upc.aventurape.platform.category.interfaces.rest.resources.CategoryResource;

/**
 * Assembler for converting Category to CategoryResource
 */
public class CategoryResourceFromEntityAssembler {
    
    /**
     * Convert a Category to a CategoryResource
     * @param entity the category to convert
     * @return the resource
     */
    public static CategoryResource toResourceFromEntity(Category entity) {
        return new CategoryResource(
                entity.getId(),
                entity.getName(),
                entity.getSubscribersCount()
        );
    }
} 