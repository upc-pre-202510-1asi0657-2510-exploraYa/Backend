// src/main/java/com/upc/aventurape/platform/publication/application/internal/outboundservices/acl/ExternalProfileService.java
package com.upc.aventurape.platform.publication.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.ProfileId;
import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileAdventurerCommand;
import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileEntrepreneurCommand;
import com.upc.aventurape.platform.profiles.domain.model.queries.GetProfileAdventurerByEmailQuery;
import com.upc.aventurape.platform.profiles.domain.model.queries.GetProfileEntrepreneurByEmailQuery;
import com.upc.aventurape.platform.profiles.domain.services.ProfileAdventureCommandService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileAdventureQueryService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileEntrepreneurCommandService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileEntrepreneurQueryService;

import java.util.Optional;

@Service
public class ExternalProfileService {

  private final ProfileAdventureCommandService profileAdventureCommandService;
  private final ProfileEntrepreneurCommandService profileEntrepreneurCommandService;
  private final ProfileAdventureQueryService profileAdventureQueryService;
  private final ProfileEntrepreneurQueryService profileEntrepreneurQueryService;

  public ExternalProfileService(ProfileAdventureCommandService profileAdventureCommandService,
                                ProfileEntrepreneurCommandService profileEntrepreneurCommandService,
                                ProfileAdventureQueryService profileAdventureQueryService,
                                ProfileEntrepreneurQueryService profileEntrepreneurQueryService) {
    this.profileAdventureCommandService = profileAdventureCommandService;
    this.profileEntrepreneurCommandService = profileEntrepreneurCommandService;
    this.profileAdventureQueryService = profileAdventureQueryService;
    this.profileEntrepreneurQueryService = profileEntrepreneurQueryService;
  }

  public Optional<ProfileId> fetchProfileIdByEmail(String email, String profileType) {
    if ("adventurer".equalsIgnoreCase(profileType)) {
      var getProfileByEmailQuery = new GetProfileAdventurerByEmailQuery(email);
      var profile = profileAdventureQueryService.handle(getProfileByEmailQuery);
      return profile.map(p -> new ProfileId(p.getId()));
    } else if ("entrepreneur".equalsIgnoreCase(profileType)) {
      var getProfileByEmailQuery = new GetProfileEntrepreneurByEmailQuery(email);
      var profile = profileEntrepreneurQueryService.handle(getProfileByEmailQuery);
      return profile.map(p -> new ProfileId(p.getId()));
    } else {
      throw new IllegalArgumentException("Invalid profile type: " + profileType);
    }
  }

  public Optional<ProfileId> createAdventurerProfile(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, String gender) {

    CreateProfileAdventurerCommand command = new CreateProfileAdventurerCommand(firstName,  lastName,  email,  street,  number,  city,  postalCode,  country,  gender);
    var profile = profileAdventureCommandService.handle(command);
    return profile.map(p -> new ProfileId(p.getId()));
  }

  public Optional<ProfileId> createEntrepreneurProfile(String email, String street, String number, String city, String postalCode, String country, String name) {

    CreateProfileEntrepreneurCommand command = new CreateProfileEntrepreneurCommand(email,  street,  number,  city,  postalCode,  country,  name);
    var profile = profileEntrepreneurCommandService.handle(command);
    return profile.map(p -> new ProfileId(p.getId()));
  }

  /*
  public Optional<ProfileId> createProfile(String firstName, String lastName, String email,
      String street, String number, String city, String state, String zipCode, String country, String gender, String profileType) {

    if ("adventurer".equalsIgnoreCase(profileType)) {
      return createAdventurerProfile(firstName,  lastName,  email,  street,  number,  city,  postalCode,  country,  gender);
    } else if ("entrepreneur".equalsIgnoreCase(profileType)) {
      return createEntrepreneurProfile(email,  street,  number,  city,  postalCode,  country,  name);
    } else {
      throw new IllegalArgumentException("Invalid profile type: " + profileType);
    }
  }
  */

}