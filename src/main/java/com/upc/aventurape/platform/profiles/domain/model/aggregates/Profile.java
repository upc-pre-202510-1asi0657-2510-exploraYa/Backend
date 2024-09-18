package com.upc.aventurape.platform.profiles.domain.model.aggregates;

import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.upc.aventurape.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.upc.aventurape.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    private String name;

    //atributos del usuario
    private String lastName;
    private String birthDate;
    private String gender;

    //atributos del emprendedor
    private String location;
    private String category; //sector

    public Profile() {
    }
    public Profile(String name, String lastName, String birthDate,
                   String gender, String location, String category) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender=gender;
        this.location = location;
        this.category = category;
    }
    public Profile(CreateProfileCommand command){
        this.name = command.name();
        this.lastName = command.lastName();
        this.birthDate = command.birthDate();
        this.gender = command.gender();
        this.location = command.location();
        this.category = command.category();
    }

    public Profile update(String name, String lastName, String birthDate,
                          String gender, String location, String category) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.location = location;
        this.category = category;
        return this;
    }

}
