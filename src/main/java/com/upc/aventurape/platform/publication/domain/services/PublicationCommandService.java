package com.upc.aventurape.platform.publication.domain.services;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.commands.*;

import java.util.Optional;

public interface PublicationCommandService {
    Publication handle(CreatePublicationCommand command);
    Optional<Publication> handle(UpdatePublicationCommand command);
    void handle(DeletePublicationCommand command);
    Publication handle(AddCommentToPublicationCommand command);
    void handle(AssignEntrepreneurToPublicationCommand command);
}
