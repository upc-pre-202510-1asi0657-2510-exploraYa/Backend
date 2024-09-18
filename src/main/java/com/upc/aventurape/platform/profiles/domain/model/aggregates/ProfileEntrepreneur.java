package com.upc.aventurape.platform.profiles.domain.model.aggregates;

import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileEntrepreneurCommand;
import com.upc.aventurape.platform.profiles.domain.model.valueobjects.NameEntrepreneurship;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class ProfileEntrepreneur extends Profile{
    @Embedded
    private NameEntrepreneurship name;

    public ProfileEntrepreneur() {
        super();
    }
    public ProfileEntrepreneur(String email, String street, String number, String city, String postalCode, String country, String name){
        super(email, street, number, city, postalCode, country);
        this.name = new NameEntrepreneurship(name);
    }
    public ProfileEntrepreneur(CreateProfileEntrepreneurCommand command){
        super(command.email(), command.street(), command.number(), command.city(), command.postalCode(), command.country());
        this.name = new NameEntrepreneurship(command.name());
    }
    @Override
    public String toString() {
        return "ProfileEntrepreneur{" +
                "name=" + name +
                ", email=" + getEmail() +
                ", address=" + getAddress() +
                '}';
    }

    public String getName() { return name.nameEntrepreneurship();}
}
