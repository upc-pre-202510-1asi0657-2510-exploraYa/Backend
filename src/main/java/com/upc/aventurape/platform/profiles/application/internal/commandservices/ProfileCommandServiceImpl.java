package com.upc.aventurape.platform.profiles.application.internal.commandservices;

import com.upc.aventurape.platform.profiles.domain.model.aggregates.Profile;
import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.upc.aventurape.platform.profiles.domain.model.commands.DeleteProfileCommand;
import com.upc.aventurape.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.upc.aventurape.platform.profiles.domain.services.ProfileCommandService;
import com.upc.aventurape.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var result = profileRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("Profile does not exist");
        var profileToUpdate = result.get();
        try {
            var updatedProfile = profileRepository.save(profileToUpdate.update
                    (command.name(), command.lastName(), command.birthDate(),
                            command.gender(), command.location(), command.category()));
            return Optional.of(updatedProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProfileCommand command) {
        if(!profileRepository.existsById(command.profileId())){
            throw new IllegalArgumentException("Profile does not exist");
        }
        try {
            profileRepository.deleteById(command.profileId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting profile: " + e.getMessage());
        }
    }

}
