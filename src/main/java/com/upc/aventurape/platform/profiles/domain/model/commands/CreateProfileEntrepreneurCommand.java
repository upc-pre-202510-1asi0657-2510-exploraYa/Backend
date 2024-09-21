package com.upc.aventurape.platform.profiles.domain.model.commands;

public record CreateProfileEntrepreneurCommand(String email, String street, String number, String city, String postalCode, String country, String name) {
}
