package com.upc.aventurape.platform.publication.application.internal.outboundservices.acl;

import com.upc.aventurape.platform.category.interfaces.acl.CategoryContextFacade;
import com.upc.aventurape.platform.category.interfaces.acl.transform.CategoryDto;
import com.upc.aventurape.platform.category.interfaces.acl.transform.PublicationIdDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * External service for accessing the Category bounded context
 * This class is part of the ACL (Anti-Corruption Layer)
 */
@Service
public class ExternalCategoryService {
    
    private final CategoryContextFacade categoryContextFacade;
    
    public ExternalCategoryService(CategoryContextFacade categoryContextFacade) {
        this.categoryContextFacade = categoryContextFacade;
    }
    
    /**
     * Get categories for a publication
     * @param publicationId the publication ID
     * @return list of category DTOs
     */
    public List<CategoryDto> getCategoriesByPublicationId(Long publicationId) {
        return categoryContextFacade.getCategoriesByPublicationId(publicationId);
    }
    
    /**
     * Get publication IDs for a category
     * @param categoryId the category ID
     * @return list of publication IDs
     */
    public List<Long> getPublicationIdsByCategoryId(Long categoryId) {
        return categoryContextFacade.getPublicationIdsByCategoryId(categoryId)
                .stream()
                .map(PublicationIdDto::getPublicationId)
                .collect(Collectors.toList());
    }
    
    /**
     * Assign a category to a publication
     * @param publicationId the publication ID
     * @param categoryId the category ID
     */
    public void assignCategoryToPublication(Long publicationId, Long categoryId) {
        categoryContextFacade.assignCategoryToPublication(publicationId, categoryId);
    }
    
    /**
     * Remove a category from a publication
     * @param publicationId the publication ID
     * @param categoryId the category ID
     */
    public void removeCategoryFromPublication(Long publicationId, Long categoryId) {
        categoryContextFacade.removeCategoryFromPublication(publicationId, categoryId);
    }
    
    /**
     * Get all categories
     * @return list of category DTOs
     */
    public List<CategoryDto> getAllCategories() {
        return categoryContextFacade.getAllCategories();
    }
    
    /**
     * Get popular categories
     * @param limit maximum number of categories to return
     * @return list of category DTOs
     */
    public List<CategoryDto> getPopularCategories(int limit) {
        return categoryContextFacade.getPopularCategories(limit);
    }
    
    /**
     * Get categories subscribed by a user
     * @param userId the user ID
     * @return list of category DTOs
     */
    public List<CategoryDto> getSubscribedCategoriesByUserId(Long userId) {
        return categoryContextFacade.getSubscribedCategoriesByUserId(userId);
    }
} 