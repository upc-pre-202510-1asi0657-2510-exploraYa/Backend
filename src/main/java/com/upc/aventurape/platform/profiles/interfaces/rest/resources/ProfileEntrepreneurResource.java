package com.upc.aventurape.platform.profiles.interfaces.rest.resources;

public record ProfileEntrepreneurResource(
        Long id,
        String name,
        String email,
        String streetAddress
) {
}
