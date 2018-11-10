package de.tud.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends EntityObject{
    public Person() {
    }

    public enum Gender{
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    @Column(name = "person_givenName")
    private String givenName;

    @Column(name = "person_familyName")
    private String familyName;

    @Column(name = "person_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "person_email")
    private String email;

    @Column(name = "person_birthday")
    private LocalDate birthday;

    @Column(name = "person_phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Diary diary;

    public Long getId(){
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

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}

