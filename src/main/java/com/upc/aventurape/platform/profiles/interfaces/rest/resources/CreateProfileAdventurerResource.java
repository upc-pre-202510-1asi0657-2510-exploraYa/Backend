package com.upc.aventurape.platform.profiles.interfaces.rest.resources;

public record CreateProfileAdventurerResource (
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country,
        String gender
) { }
