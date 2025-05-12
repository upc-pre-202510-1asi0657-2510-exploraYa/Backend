package com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.entities.Favorite;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.EntrepreneurId;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.ProfileId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    Boolean existsByAdventureNameActivity(String nameActivity);
    List<Publication> findByEntrepreneurId(EntrepreneurId entrepreneurId);

    boolean existsByEntrepreneurId(EntrepreneurId entrepreneurId);
    
    /**
     * Encuentra publicaciones por lista de IDs con paginación
     * @param ids lista de IDs de publicaciones
     * @param pageable configuración de paginación
     * @return página de publicaciones
     */
    Page<Publication> findByIdIn(List<Long> ids, Pageable pageable);
}
