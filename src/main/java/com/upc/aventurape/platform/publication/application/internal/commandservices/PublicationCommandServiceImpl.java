package com.upc.aventurape.platform.publication.application.internal.commandservices;

import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.commands.*;
import com.upc.aventurape.platform.publication.domain.model.entities.Adventure;
import com.upc.aventurape.platform.publication.domain.model.entities.Comment;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.EntrepreneurId;
import com.upc.aventurape.platform.publication.domain.services.PublicationCommandService;
import com.upc.aventurape.platform.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicationCommandServiceImpl  implements PublicationCommandService {

    private final PublicationRepository publicationRepository;

    public PublicationCommandServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }


    @Override
    public Publication handle(CreatePublicationCommand command) {
        if (publicationRepository.existsByAdventureNameActivity(command.nameActivity())) {
            throw new IllegalArgumentException("Publication already exists");
        }
        var adventure = new Adventure(command.nameActivity(), command.description(), command.cantPeople(), command.timeDuration());
        var publication = new Publication(command.cost(), new EntrepreneurId(command.userId()), adventure,
                command.image(), command.timeDuration(),command.cantPeople());
        publicationRepository.save(publication);
        return publication;
    }

    @Override
    public Optional<Publication> handle(UpdatePublicationCommand command) {
       if (!publicationRepository.existsById(command.publicationId())) {
            throw new IllegalArgumentException("Publication does not exist");
        }
        var publication = publicationRepository.findById(command.publicationId()).get();
        publication.updateCost(command.cost());
        publication.updateEntrepreneurId(command.entrepreneurId());
        var adventure = publication.getAdventure();
        adventure.setNameActivity(command.adventure().getNameActivity());
        adventure.setDescription(command.adventure().getDescription());
        adventure.setCantPeople(command.adventure().getCantPeople());
        adventure.setTimeDuration(command.adventure().getTimeDuration());
        publicationRepository.save(publication);
        return Optional.of(publication);
    }

    @Override
    public void handle(DeletePublicationCommand command) {
        if (!publicationRepository.existsById(command.publicationId())) {
            throw new IllegalArgumentException("Publication does not exist");
        }
    }

    @Override
    public Publication handle(AddCommentToPublicationCommand command) {
       if (!publicationRepository.existsById(command.publicationId())) {
            throw new IllegalArgumentException("Publication does not exist");
        }
        var publication = publicationRepository.findById(command.publicationId()).get();
        var comment = publication.getComments();
        comment.add(new Comment(publication, command.content(), command.rating()));
        publicationRepository.save(publication);
        return publication;
    }

    @Override
    public void handle(AssignEntrepreneurToPublicationCommand command) {

        if (!publicationRepository.existsById(command.publicationId())) {
            throw new IllegalArgumentException("Publication does not exist");
        }
        var publication = publicationRepository.findById(command.publicationId()).get();
        publication.updateEntrepreneurId(command.entrepreneurId());
        publicationRepository.save(publication);
    }
}
