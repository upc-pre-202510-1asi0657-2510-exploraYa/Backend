package com.upc.aventurape.platform.category.domain.model.entities;

import com.upc.aventurape.platform.category.domain.model.aggregates.Category;
import com.upc.aventurape.platform.category.domain.model.valueobjects.UserId;
import com.upc.aventurape.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * CategorySubscription entity
 * This class represents a subscription of a user to a category.
 */
@Getter
@Setter
@Entity
public class CategorySubscription extends AuditableModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date subscribedAt;
    
    public CategorySubscription() {
        this.subscribedAt = new Date();
    }
    
    public CategorySubscription(UserId userId, Category category) {
        this.userId = userId;
        this.category = category;
        this.subscribedAt = new Date();
    }
} 