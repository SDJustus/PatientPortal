package de.tud.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends EntityObject{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_postCode")
    private int postCode;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_country")
    private String country;

    @OneToOne(mappedBy = "address")
    private Person person;

    public Address(String street, String number, int postCode, String city, String country) {
        this.street = street;
        this.number = number;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }
/*
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }*/

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Long getId() {
        return id;
    }
}