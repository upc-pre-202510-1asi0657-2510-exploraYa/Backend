package com.upc.aventurape.platform.profiles.interfaces.rest;

import com.upc.aventurape.platform.profiles.domain.model.queries.*;
import com.upc.aventurape.platform.profiles.domain.services.ProfileCommandService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileQueryService;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfilesController(ProfileQueryService profileQueryService,
                              ProfileCommandService profileCommandService) {

        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(
        @RequestBody CreateProfileResource resource) {

        var createProfileCommand = CreateProfileCommandFromResourceAssembler
            .toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResource> updateProfile(
        @PathVariable Long profileId,
        @RequestBody UpdateProfileResource updateProfileResource) {
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler
                .toCommandFromResource(profileId, updateProfileResource);
        var profile = profileCommandService.handle(updateProfileCommand);
        if (profile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/id/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProfileResource> getProfileByName(@PathVariable String name) {
        var getProfileByNameQuery = new GetProfileByNameQuery(name);
        var profile = profileQueryService.handle(getProfileByNameQuery);
        if (profile.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProfileResource>> getProfilesByCategory(@PathVariable String category) {
        var getProfilesByCategoryQuery = new GetProfileByCategoryQuery(category);
        var profiles = profileQueryService.handle(getProfilesByCategoryQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<ProfileResource>> getProfilesByLocation(@PathVariable String location) {
        var getProfilesByLocationQuery = new GetProfileByLocationQuery(location);
        var profiles = profileQueryService.handle(getProfilesByLocationQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var profiles = profileQueryService.handle(new GetAllProfilesQuery());
        var profileResources = profiles.stream()
            .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return new ResponseEntity<>(profileResources, HttpStatus.OK);
    }
}
