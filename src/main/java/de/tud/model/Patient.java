package de.tud.model;

import java.time.LocalDate;

public class Patient extends Person {
    public Patient(String givenName, String familyName, Gender gender, String email, LocalDate birthday, String phone, Address address) {
        super(givenName, familyName, gender, email, birthday, phone, address);
    }
}
