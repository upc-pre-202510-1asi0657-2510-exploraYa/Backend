package com.upc.aventurape.platform.publication.domain.model.aggregates;

import com.upc.aventurape.platform.publication.domain.model.entities.Adventure;
import com.upc.aventurape.platform.publication.domain.model.entities.Comment;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.CommentManager;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.EntrepreneurId;

import com.upc.aventurape.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.validation.constraints.Size;

@Setter
@Getter
@Entity
public class Publication extends AuditableAbstractAggregateRoot<Publication> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    @Embedded
    private EntrepreneurId entrepreneurId;

    @OneToOne(mappedBy = "publication", cascade = CascadeType.ALL)
    private Adventure adventure;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Embedded
    private CommentManager commentManager;


    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String image;



    @Column(nullable = false)
    @NotNull
    private Integer cost;

    public Publication() {
        this.entrepreneurId = new EntrepreneurId();
        this.commentManager = new CommentManager();
        this.image = "";
        this.cost = 0;
    }

    public Publication(Integer cost, EntrepreneurId entrepreneurId,
                       Adventure adventure, String image, Integer TimeDuration,
                       Integer cantPeople) {
        this.cost = cost;
        this.entrepreneurId = entrepreneurId;
        this.adventure = adventure;
        this.adventure.setPublication(this);
        this.commentManager = new CommentManager();
        this.image = image;
        this.rating = 0.0;

    }

    public void updateCost(Integer cost) {
        this.cost = cost;
    }

    public void updateEntrepreneurId(EntrepreneurId entrepreneurId) {
        this.entrepreneurId = entrepreneurId;
    }


    public void calculateRating() {
        double calculatedRating = commentManager.calculateRating(comments);
        this.rating =calculatedRating;
    }

    public String getNameActivity() {
        return adventure.getNameActivity();
    }

    public String getDescription() {
        return adventure.getDescription();
    }

    public Integer getTimeDuration() {
        return adventure.getTimeDuration();
    }

    public Integer getCantPeople() {
        return adventure.getCantPeople();
    }
}
