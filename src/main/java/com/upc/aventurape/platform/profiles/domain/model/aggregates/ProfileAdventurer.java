package com.upc.aventurape.platform.profiles.domain.model.aggregates;

import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileAdventurerCommand;
import com.upc.aventurape.platform.profiles.domain.model.valueobjects.Gender;
import com.upc.aventurape.platform.profiles.domain.model.valueobjects.Genders;
import com.upc.aventurape.platform.profiles.domain.model.valueobjects.PersonName;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class ProfileAdventurer extends Profile{

    @Embedded
    private PersonName name;

    @Enumerated(EnumType.STRING)
    private Genders gender;

    public ProfileAdventurer() {
        super();
    }
    public ProfileAdventurer(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, String gender) {
        super(email, street, number, city, postalCode, country);
        this.name = new PersonName(firstName, lastName);
        this.gender = new Gender(gender).convertToEnum();
    }
    public ProfileAdventurer(CreateProfileAdventurerCommand command) {
        super(command.email(), command.street(), command.number(), command.city(), command.postalCode(), command.country());
        this.name = new PersonName(command.firstName(), command.lastName());
        this.gender = new Gender(command.gender()).convertToEnum();
    }

    public String getGender() {
        return gender.toString();
    }

    @Override
    public String toString() {
        return "ProfileAdventurer{" +
                "name=" + name +
                ", gender=" + gender +
                ", email=" + this.getEmail() +
                ", address=" + this.getAddress() +
                '}';
    }
    public String getFullName() {
        return name.getFullName();
    }
}
