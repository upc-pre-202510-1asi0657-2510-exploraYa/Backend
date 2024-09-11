package com.upc.aventurape.platform.publication.application.internal.commandservices;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.commands.CreatePublicationCommand;
import com.upc.aventurape.platform.publication.domain.services.PublicationCommandService;
import com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicationCommandServiceImpl  implements PublicationCommandService {

    private final PublicationRepository publicationRepository;

    public PublicationCommandServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }


    @Override
    public Long handle(CreatePublicationCommand command) {
        var Publication = new Publication();
        Publication.setNameActivity(command.nameActivity());
        Publication.setDescription(command.description());
        Publication.setTimeDuration(command.timeDuration());
        Publication.setImage(command.image());
        Publication.setCantPeople(command.cantPeople());
        Publication.setCost(command.cost());
        try {
            publicationRepository.save(Publication);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving Publication: " + e.getMessage());
        }
        return Publication.getId();
    }
}
