package com.upc.aventurape.platform.publication.domain.services;

import com.upc.aventurape.platform.publication.domain.model.commands.CreatePublicationCommand;

public interface PublicationCommandService {
    Long handle(CreatePublicationCommand command);
}
