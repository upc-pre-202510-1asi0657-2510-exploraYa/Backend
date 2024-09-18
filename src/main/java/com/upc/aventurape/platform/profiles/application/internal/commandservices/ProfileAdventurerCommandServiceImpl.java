package com.upc.aventurape.platform.profiles.application.internal.commandservices;

import com.upc.aventurape.platform.profiles.domain.model.aggregates.ProfileAdventurer;
import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileAdventurerCommand;
import com.upc.aventurape.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.upc.aventurape.platform.profiles.domain.services.ProfileAdventureCommandService;
import com.upc.aventurape.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileAdventurerCommandServiceImpl implements ProfileAdventureCommandService {
    private final ProfileRepository profileRepository;

    public ProfileAdventurerCommandServiceImpl(ProfileRepository profileRepository, ProfileRepository profileRepository1) {
        this.profileRepository = profileRepository1;
    }

    @Override
    public Optional<ProfileAdventurer> handle(CreateProfileAdventurerCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if(profileRepository.existsAdventurerByEmail(emailAddress.address()))
            throw new IllegalArgumentException("Profile with email "+ command.email() +"already exists");
        var profileAdventurer = new ProfileAdventurer(command);
        profileRepository.save(profileAdventurer);
        return Optional.of(profileAdventurer);
    }
}
