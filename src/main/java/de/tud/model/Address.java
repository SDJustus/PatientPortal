package de.tud.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends EntityObject{

    /**
     * Holds the ID of the persistent Address object.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Holds the value for the street name.
     */
    @Column(name = "address_street")
    private String street;

    /**
     * Holds the value of the building number in the street.
     */
    @Column(name = "address_number")
    private String number;

    /**
     * Holds the value of the post code.
     */
    @Column(name = "address_postCode")
    private int postCode;

    /**
     * Holds the value of the city name.
     */
    @Column(name = "address_city")
    private String city;

    /**
     * Holds the value of the country name.
     */
    @Column(name = "address_country")
    private String country;

    /**
     * Holds the object of Person the Address object is mapped to.
     */
    @OneToOne(mappedBy = "address")
    private Person person;

    /**
     * Constructor of Address.
     * @param street
     * @param number
     * @param postCode
     * @param city
     * @param country
     */
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

    /**
     * Returns the street value of the Address object.
     * @return value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street value of the Address object.
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the number value of the Address object.
     * @return value of number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number value of the Address object.
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Returns the post code value of the Address object.
     * @return value of postCode
     */
    public int getPostCode() {
        return postCode;
    }

    /**
     * Sets the post code value of the Address object.
     * @param postCode
     */
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    /**
     * Returns the city value of the Address object.
     * @return value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city value of the Address object.
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the country value of the Address object.
     * @return value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country value of the Address object.
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the ID of the persistent Address object.
     * @return value of id
     */
    @Override
    public Long getId() {
        return id;
    }
}
