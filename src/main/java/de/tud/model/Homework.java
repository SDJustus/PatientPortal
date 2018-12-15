package de.tud.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name="homework")
public class Homework extends EntityObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name="homework_date")
    private ZonedDateTime date;
    @Column(name="homework_name")
    private String name;
    @Column(name="homework_shortDescription")
    private String shortDescription;
    @Column(name="homework_longDescription")
    private String longDescription;
    @Column(name="homework_status")
    private boolean status;                                                                                             //Status ist False wenn nicht erledigt
    @Column(name="homework_type")
    private Type type;



    public enum Type{
        QUESTIONNAIRE,
        DOKUMENT,
        EXERCISE
    }

    public Homework(){}

    public Homework(Type type, String name, String shortDescription, String longDescription){                           //neue Homework mit Status False erzeugt da keine Aufgabe direkt erledig sein soll
        this.type=type;
        this.name=name;
        this.shortDescription=shortDescription;
        this.longDescription=longDescription;
        this.status=false;

    }
    public Homework(Type type, String name, String shortDescription, String longDescription, ZonedDateTime date){                           //neue Homework mit Status False erzeugt da keine Aufgabe direkt erledig sein soll
        this.type=type;
        this.name=name;
        this.shortDescription=shortDescription;
        this.longDescription=longDescription;
        this.status=false;
        this.date= date;
    }


    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

}