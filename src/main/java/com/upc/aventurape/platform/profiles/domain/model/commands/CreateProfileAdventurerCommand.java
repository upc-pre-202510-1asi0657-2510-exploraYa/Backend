package com.upc.aventurape.platform.profiles.domain.model.commands;

public record CreateProfileAdventurerCommand(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, String gender) {
}
