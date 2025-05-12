package com.upc.aventurape.platform.category.interfaces.rest;

import com.upc.aventurape.platform.category.domain.model.aggregates.Category;
import com.upc.aventurape.platform.category.domain.model.commands.SubscribeToCategoryCommand;
import com.upc.aventurape.platform.category.domain.model.commands.UnsubscribeFromCategoryCommand;
import com.upc.aventurape.platform.category.domain.model.entities.CategorySubscription;
import com.upc.aventurape.platform.category.domain.model.queries.GetSubscribedCategoriesByUserIdQuery;
import com.upc.aventurape.platform.category.domain.services.CategoryCommandService;
import com.upc.aventurape.platform.category.domain.services.CategoryQueryService;
import com.upc.aventurape.platform.category.interfaces.rest.resources.CategoryResource;
import com.upc.aventurape.platform.category.interfaces.rest.resources.CreateSubscriptionRequest;
import com.upc.aventurape.platform.category.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import com.upc.aventurape.platform.iam.infrastructure.security.SecurityUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for category subscriptions
 */
@RestController
@RequestMapping(value = "/api/v1/category-subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Category Subscriptions")
public class CategorySubscriptionController {

    private final Logger logger = LoggerFactory.getLogger(CategorySubscriptionController.class);
    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    public CategorySubscriptionController(CategoryCommandService categoryCommandService,
                                         CategoryQueryService categoryQueryService) {
        this.categoryCommandService = categoryCommandService;
        this.categoryQueryService = categoryQueryService;
    }

    /**
     * Get subscribed categories for the current user
     * @return list of category resources
     */
    @GetMapping("/my-subscriptions")
    public ResponseEntity<List<CategoryResource>> getMySubscribedCategories() {
        Long userId = SecurityUtils.getCurrentUserId();
        logger.info("Obteniendo categorías suscritas para el usuario actual: {}", userId);
        
        var query = new GetSubscribedCategoriesByUserIdQuery(userId);
        List<Category> categories = categoryQueryService.handle(query);
        
        logger.info("Categorías encontradas para el usuario actual: {}", categories.size());
        
        List<CategoryResource> resources = categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(resources);
    }

    /**
     * Get subscribed categories for a specific user by ID
     * @param userId the user ID
     * @return list of category resources
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryResource>> getUserSubscribedCategories(@PathVariable Long userId) {
        logger.info("Buscando categorías para el usuario con ID: {}", userId);
        
        var query = new GetSubscribedCategoriesByUserIdQuery(userId);
        List<Category> categories = categoryQueryService.handle(query);
        
        logger.info("Categorías encontradas para userId {}: {}", userId, categories.size());
        
        if (categories.isEmpty()) {
            logger.warn("No se encontraron categorías para el usuario con ID: {}", userId);
        }
        
        List<CategoryResource> resources = categories.stream()
                .map(category -> {
                    CategoryResource resource = CategoryResourceFromEntityAssembler.toResourceFromEntity(category);
                    logger.debug("Categoría convertida: ID={}, Nombre={}, Suscriptores={}",
                            category.getId(), category.getName(), category.getSubscribersCount());
                    return resource;
                })
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(resources);
    }

    /**
     * Crear una nueva suscripción para cualquier usuario a una categoría
     * @param request objeto de solicitud con userId y categoryId
     * @return respuesta de éxito
     */
    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        logger.info("Creando suscripción para el usuario {} a la categoría {}", 
                request.getUserId(), request.getCategoryId());
        
        var command = new SubscribeToCategoryCommand(request.getUserId(), request.getCategoryId());
        CategorySubscription subscription = categoryCommandService.handle(command);
        
        logger.info("Suscripción creada exitosamente con ID: {}", subscription.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Suscripción creada correctamente con ID: " + subscription.getId());
    }

    /**
     * Unsubscribe from a category
     * @param categoryId the category ID
     * @return no content response
     */
    @DeleteMapping("/unsubscribe/{categoryId}")
    public ResponseEntity<Void> unsubscribeFromCategory(@PathVariable Long categoryId) {
        Long userId = SecurityUtils.getCurrentUserId();
        logger.info("Usuario {} cancelando suscripción a la categoría {}", userId, categoryId);
        
        var command = new UnsubscribeFromCategoryCommand(userId, categoryId);
        categoryCommandService.handle(command);
        
        logger.info("Usuario {} canceló suscripción exitosamente a la categoría {}", userId, categoryId);
        return ResponseEntity.noContent().build();
    }
} 