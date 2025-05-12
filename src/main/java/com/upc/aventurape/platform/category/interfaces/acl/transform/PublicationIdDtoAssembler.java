package com.upc.aventurape.platform.category.interfaces.acl.transform;

import com.upc.aventurape.platform.category.domain.model.valueobjects.PublicationId;

/**
 * Assembler for converting PublicationId to PublicationIdDto
 */
public class PublicationIdDtoAssembler {
    
    /**
     * Convert a PublicationId to a PublicationIdDto
     * @param publicationId the publication ID to convert
     * @return the DTO
     */
    public static PublicationIdDto toDto(PublicationId publicationId) {
        return new PublicationIdDto(publicationId.getPublicationId());
    }
} 