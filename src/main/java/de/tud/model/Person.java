package de.tud.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends EntityObject{

    /**
     * Secondary (empty) constructor of Person.
     */
    public Person() {
    }

    /**
     * Determines the possible gender values of the Person object.
     */
    public enum Gender{
        FEMALE, MALE
    }

    /**
     * Primary constructor of Person.
     * @param givenName
     * @param familyName
     * @param gender
     * @param email
     * @param birthday
     * @param phone
     * @param address
     */
    public Person(String givenName, String familyName, Gender gender, String email, LocalDate birthday, String phone, Address address) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Holds the ID of the persistent Person object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    /**
     * Holds the given name value of the Person object.
     */
    @Column(name = "person_givenName")
    private String givenName;

    /**
     * Holds the family name value of the Person object.
     */
    @Column(name = "person_familyName")
    private String familyName;

    /**
     * Holds the gender value of the Person object.
     */
    @Column(name = "person_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Holds the email value of the Person object.
     */
    @Column(name = "person_email")
    private String email;

    /**
     * Holds the birthday value of the Person object.
     */
    @Column(name = "person_birthday")
    private LocalDate birthday;

    /**
     * Holds the phone nuber value of the Person object.
     */
    @Column(name = "person_phone")
    private String phone;

    /**
     * Holds the instance of Address the Person object is mapped to.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    /**
     * Holds the instance of Diary the Person object is mapped to.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Diary diary;

    /**
     * Returns the ID of the persistent Person object.
     * @return value of id
     */
    public Long getId(){
        return id;
    }

    /**
     * Returns the givenName value of the Person object.
     * @return value of givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the givenName value of the Person object.
     * @param givenName
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Returns the familyName value of the Person object.
     * @return value of familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the familyName value of the Person object.
     * @param familyName
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Returns the gender value of the Person object.
     * @return value of gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender value of the Person object.
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns the email value of the Person object.
     * @return value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email value of the Person object.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the birthday value of the Person object.
     * @return value of birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday value of the Person object.
     * @param birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Returns the phone value of the Person object.
     * @return value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone value of the Person object.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the Address object held by the Person object.
     * @return instance of Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address value of the Person object.
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the Diary object held by the Person object.
     * @return instance of Diary
     */
    public Diary getDiary() {
        return diary;
    }

    /**
     * Sets the diary value of the Person object.
     * @param diary
     */
    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}

