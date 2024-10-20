package com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories;

import com.upc.aventurape.platform.publication.domain.model.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePublicationRepository extends JpaRepository<Favorite, Long> {
}