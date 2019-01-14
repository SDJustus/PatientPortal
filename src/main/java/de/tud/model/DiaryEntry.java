package de.tud.model;

import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diary_entry")
public class DiaryEntry extends EntityObject {

    /**
     * Holds the ID of the persistent DiaryEntry object.
     */
    @Id
    @SequenceGenerator(name = "DiaryEntryGenerator", sequenceName = "DiaryEntrySequence", allocationSize = 1)
    @GeneratedValue(generator = "DiaryEntryGenerator")
    private long id;

    /**
     * Holds the date value of the DiaryEntry object.
     */
    private LocalDateTime date;

    /**
     * Holds Symptom objects of the DiaryEntry object as a set.
     */
    @OneToMany(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Symptom> symptom;

    /**
     * Holds Welfare objects of the DiaryEntry object as a set.
     */
    @OneToMany(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Welfare> welfare;

    /**
     * Holds a VitalData object belonging to the DiaryEntry object.
     */
    @OneToOne(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name="vital_data_id")
    private VitalData vitalData;

    /**
     * Returns the date value of the DiaryEntry object.
     * @return value of date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * (empty) Constructor of DiaryEntry.
     */
    public DiaryEntry(){}

    /**
     * Constructor of DiaryEntry.
     * @param date
     * @param symptom
     * @param vitalData
     * @param welfare
     */
    public DiaryEntry(LocalDateTime date, Set<Symptom> symptom, VitalData vitalData, Set<Welfare> welfare){
        this.date = date;
        this.symptom = symptom;
        this.vitalData = vitalData;
        this.welfare=welfare;
    }

    /**
     * Constructor of DiaryEntry.
     * @param date
     * @param symptom
     */
    public DiaryEntry(LocalDateTime date, Set<Symptom> symptom){
        this.date = date;
        this.symptom = symptom;

    }

    /**
     * Constructor of DiaryEntry.
     * @param date
     * @param vitalData
     */
    public DiaryEntry(LocalDateTime date,  VitalData vitalData){
        this.date = date;
        this.vitalData = vitalData;

    }

    /**
     * Constructor of DiaryEntry.
     * @param date
     * @param welfare
     */
    public DiaryEntry(LocalDateTime date, HashSet<Welfare> welfare){
        this.date = date;
        this.welfare=welfare;
    }

    /**
     * Returns the ID of the persistent DiaryEntry object.
     * @return value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the date value of the DiaryEntry.
     * @param date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the Symptom objects belonging to the DiaryEntry object as a set.
     * @return set of Symptom
     */
    public Set<Symptom> getSymptom() {
        return symptom;
    }

    /**
     * Sets the value of symptom as set of given Symptom objects.
     * @param symptom
     */
    public void setSymptom(Set<Symptom> symptom) {
        this.symptom = symptom;
    }

    /**
     * Sets the vitalData value of the DiaryEntry object.
     * @param vitalData
     */
    public void setVitalData(VitalData vitalData){
        this.vitalData = vitalData;
    }

    /**
     * Returns the VitalData object held in the DiaryEntry object.
     * @return instance of VitalData
     */
    public VitalData getVitalData(){
        return this.vitalData;
    }

    /**
     * Returns the set of Welfare objects held in the DiaryEntry object.
     * @return set of Welfare
     */
    public Set<Welfare> getWelfare() {
        return welfare;
    }

    /**
     * Sets the value of welfare as a set of Welfare objects.
     * @param welfare
     */
    public void setWelfare(Set<Welfare> welfare) {
        this.welfare = welfare;
    }
}
