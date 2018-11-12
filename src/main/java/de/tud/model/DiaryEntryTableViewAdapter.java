package de.tud.model;

import de.tud.model.symptom.Symptom;
import javafx.beans.property.SimpleStringProperty;

public class DiaryEntryTableViewAdapter {
    private String date;
    private Symptom symptom;

    public DiaryEntryTableViewAdapter(String date, Symptom symptom){
        this.date = date;
        this.symptom = symptom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymptom() {
        return symptom.toString();
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
