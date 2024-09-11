package com.upc.aventurape.platform.publication.domain.model.aggregates;

import com.upc.aventurape.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import jakarta.validation.constraints.Size;

@Setter
@Getter
@Entity
public class Publication extends AuditableAbstractAggregateRoot<Publication> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String nameActivity;

    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String description;

    @Column(nullable = false)
    @NotNull
    private Integer timeDuration;

    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String image;

    @Column(nullable = false)
    @NotNull
    private Integer cantPeople;

    @Column(nullable = false)
    @NotNull
    private Integer cost;

    public Publication() {
    }

    public Publication(String nameActivity, String description, Integer timeDuration, String image, Integer cantPeople, Integer cost) {
        this.nameActivity = nameActivity;
        this.description = description;
        this.timeDuration = timeDuration;
        this.image = image;
        this.cantPeople = cantPeople;
        this.cost = cost;
    }
}
