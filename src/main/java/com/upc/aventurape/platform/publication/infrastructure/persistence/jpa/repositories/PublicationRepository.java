package com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
