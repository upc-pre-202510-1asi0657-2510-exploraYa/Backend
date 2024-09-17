package com.upc.aventurape.platform.publication.domain.model.entities;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Adventure extends AuditableModel {

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

    @OneToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @Column(nullable = false)
    @NotNull
    private Integer cantPeople;

    @Column(nullable = false)
    @NotNull
    private Integer timeDuration;

    public Adventure() {
        this.nameActivity = "";
        this.description = "";
        this.cantPeople = 0;
        this.timeDuration = 0;
    }

    public Adventure(String nameActivity, String description, Integer cantPeople, Integer timeDuration) {
        this.nameActivity = nameActivity;
        this.description = description;
        this.cantPeople = 0;
        this.timeDuration = 0;
    }


}
