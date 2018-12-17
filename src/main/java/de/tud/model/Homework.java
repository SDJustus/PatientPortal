package de.tud.model;

import javax.persistence.*;

@Entity
@Table(name="homework")
public class Homework extends EntityObject {

    @Id
    @SequenceGenerator(name = "HomeworkGenerator", sequenceName = "HomeworkSequence", allocationSize = 1)
    @GeneratedValue(generator = "HomeworkGenerator")
    private long id;
    @Column(name="homework_name")
    private String name;
    @Column(name="homework_description")
    private String description;
    @Column(name="homework_status")
    private boolean status;                                                                                             //Status ist False wenn nicht erledigt
    @Column(name="homework_type")
    private Type type;

    public enum Type{
        QUESTIONNAIRE,
        DOCUMENT,
        EXERCISE
    }

    public Homework(){}

    public Homework(Type type, String name, String description){                           //neue Homework mit Status False erzeugt da keine Aufgabe direkt erledig sein soll
        this.type=type;
        this.name=name;
        this.description = description;
        this.status=false;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String shortDescription) {
        this.description = shortDescription;
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
}
