package de.tud.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Person")
public class Person {
    public Person() {
    }

    enum Gender{
        FEMALE, MALE
    }

    public Person(String givenName, String familyName, Gender gender, String email, LocalDate birthday, String phone, Address address) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private int id;

    @Column(name = "person_givenName")
    private String givenName;

    @Column(name = "person_familyName")
    private String familyName;

    @Column(name = "person_gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "person_email")
    private String email;

    @Column(name = "person_birthday")
    private LocalDate birthday;

    @Column(name = "person_phone")
    private String phone;

    @OneToMany(mappedBy = "Address")
    private Address address;

    public int getId(){
        return id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
