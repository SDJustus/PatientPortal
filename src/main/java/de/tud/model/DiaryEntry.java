package de.tud.model;

import de.tud.model.symptom.Symptom;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "diary_entry")
public class DiaryEntry extends EntityObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime date;

    @OneToMany(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Symptom> symptom;

    public LocalDateTime getDate() {
        return date;
    }

    public DiaryEntry(){}

    public DiaryEntry(LocalDateTime date, Set<Symptom> symptom){
        this.date = date;
        this.symptom = symptom;
    }

    public Long getId() {
        return id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public Set<Symptom> getSymptom() {
        return symptom;
    }

    public void setSymptom(Set<Symptom> symptom) {
        this.symptom = symptom;
    }
}
