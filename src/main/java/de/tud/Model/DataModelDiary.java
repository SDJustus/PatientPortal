package de.tud.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class DataModelDiary {

    private LocalDateTime date;

    private Symptom symptom;

    private long diaryID;

    public DataModelDiary(LocalDateTime date, Symptom symptom){
        this.date = date;
        this.symptom = symptom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
