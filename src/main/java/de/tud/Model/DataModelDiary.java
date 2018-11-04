package de.tud.Model;

public class DataModelDiary {
    private String date;
    private Symptom symptom;

    public DataModelDiary(String date, Symptom symptom){
        this.date = date;
        this.symptom = symptom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
