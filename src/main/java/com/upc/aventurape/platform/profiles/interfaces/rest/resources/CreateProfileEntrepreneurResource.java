package com.upc.aventurape.platform.profiles.interfaces.rest.resources;

public record CreateProfileEntrepreneurResource(
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country,
        String name
) {
}
