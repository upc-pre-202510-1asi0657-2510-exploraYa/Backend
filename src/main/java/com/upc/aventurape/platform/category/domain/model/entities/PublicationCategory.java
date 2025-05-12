package com.upc.aventurape.platform.category.domain.model.entities;

import com.upc.aventurape.platform.category.domain.model.aggregates.Category;
import com.upc.aventurape.platform.category.domain.model.valueobjects.PublicationId;
import com.upc.aventurape.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * PublicationCategory entity
 * This class represents a relationship between a publication and a category.
 */
@Getter
@Setter
@Entity
public class PublicationCategory extends AuditableModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    private PublicationId publicationId;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    public PublicationCategory() {
    }
    
    public PublicationCategory(PublicationId publicationId, Category category) {
        this.publicationId = publicationId;
        this.category = category;
    }
} 