package com.upc.aventurape.platform.profiles.interfaces.rest.resources;

public record ProfileAdventurerResource (
        Long id,
        String fullName,
        String gender,
        String email,
        String streetAddress
){ }
