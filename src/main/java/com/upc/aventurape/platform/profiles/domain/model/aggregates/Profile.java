package com.upc.aventurape.platform.profiles.domain.model.aggregates;

import com.upc.aventurape.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.upc.aventurape.platform.profiles.domain.model.valueobjects.StreetAddress;
import com.upc.aventurape.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Usamos herencia con "JOINED" para que las subclases tengan sus propias tablas
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))
    })
    private EmailAddress email;

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country"))
    })
    private StreetAddress address;

    public Profile(String email, String street, String number, String city, String postalCode, String country) {
        this.email = new EmailAddress(email);
        this.address = new StreetAddress(street, number, city, postalCode, country);
    }

    public Profile() { }

    public void updateEmail(String email) { this.email = new EmailAddress(email);}
    public void updateAddress(String street, String number, String city, String postalCode, String country) { this.address = new StreetAddress(street, number, city, postalCode, country); }
    public String getEmailAddress() { return this.email.address(); }

    public String getStreetAddress() { return this.address.street(); }
}
