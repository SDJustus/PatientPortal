package de.tud.Model;

public class DataModelDiary {
    private String date;
    private String symptom;

    public DataModelDiary(String date, String symptom){
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
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
