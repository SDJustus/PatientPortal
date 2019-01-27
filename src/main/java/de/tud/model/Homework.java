package de.tud.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name="homework")
public class Homework extends EntityObject {

    /**
     * Holds the ID of the persistent Homework object.
     */
    @Id
    @SequenceGenerator(name = "HomeworkGenerator", sequenceName = "HomeworkSequence", allocationSize = 1)
    @GeneratedValue(generator = "HomeworkGenerator")
    private long id;
    @Column (name="homework_date")
    private ZonedDateTime date;
    /**
     * Holds the name value of the specific Homework object.
     */
    @Column(name="homework_name")
    private String name;

    /**
     * Holds the value of the description of the Homework object.
     */
    @Column(name="homework_description")
    private String description;

    /**
     * Holds the boolean status value of the Homework object.
     */
    @Column(name="homework_status")
    private boolean status; //Status ist False wenn nicht erledigt

    /**
     * Holds the predefined Type value of the Homework object.
     */
    @Column(name="homework_type")
    private Type type;



    /**
     * Determines the possible type values of the Homework object.
     */
    public enum Type{
        QUESTIONNAIRE,
        DOCUMENT,
        EXERCISE
    }

    /**
     * Secondary (empty) constructor of Homework.
     */
    public Homework(){}

    /**
     * Primary constructor of Homework.
     * @param type
     * @param name
     * @param description
     */
    public Homework(Type type, String name, String description, ZonedDateTime date){
        this.type=type;
        this.name=name;
        this.description = description;
        this.status=false;
        this.date = date;
    }
    public Homework(Type type, String name, String description) {
        this.type=type;
        this.name=name;
        this.description = description;
        this.status=false;
    }
    /**
     * Returns the ID of the persistent Homework object.
     * @return value of id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Returns the name value of the Homework object.
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name value of the Homework object.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description value of the Homework object.
     * @return value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description value of the Homework object.
     * @param shortDescription
     */
    public void setDescription(String shortDescription) {
        this.description = shortDescription;
    }

    /**
     * Returns the boolean status value of the Homework object.
     * @return value of status
     */
    public Boolean isStatus() {
        return status;
    }

    /**
     * Sets the boolean status value of the Homework object.
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Returns the type value of the Homework object.
     * @return value of type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type value of the Homework object.
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
    public LocalDateTime getLocalDateTime(){
        return date.toLocalDateTime();
    }


}