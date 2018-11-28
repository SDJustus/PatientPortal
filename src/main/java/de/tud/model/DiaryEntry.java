package de.tud.model;

import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;

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

    @OneToMany(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Welfare> welfare;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="vital_data_id")
    private VitalData vitalData;

    public LocalDateTime getDate() {
        return date;
    }

    public DiaryEntry(){}

    public DiaryEntry(LocalDateTime date, Set<Symptom> symptom, VitalData vitalData, Set<Welfare> welfare){
        this.date = date;
        this.symptom = symptom;
        this.vitalData = vitalData;
        this.welfare=welfare;
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

    public void setVitalData(VitalData vitalData){
        this.vitalData = vitalData;
    }

    public VitalData getVitalData(){
        return this.vitalData;
    }

    public Set<Welfare> getWelfare() {
        return welfare;
    }

    public void setWelfare(Set<Welfare> welfare) {
        this.welfare = welfare;
    }
}
