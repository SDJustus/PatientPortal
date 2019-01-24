package de.tud.view.diaryEvaluation;

import de.tud.model.VitalData;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiaryEvaluationUIModel extends VitalData {
    //Symptom Table
    private Symptom symptom;

    //Welfare Table
    private Welfare welfare;

    private LocalDateTime date;
    private LocalDateTime clock;

    private ArrayList<DiaryEvaluationUIModel> subEntries = new ArrayList<>();


    //Constructor for SymptomTable
    public DiaryEvaluationUIModel(LocalDateTime d, Symptom s) {
        this.symptom = s;
        this.date = d;
        this.clock = d;
    }

    //Constructor for VitalDataTable
    public DiaryEvaluationUIModel(LocalDateTime date, float height, float weight, int bloodPressureFirstValue,
                                  int bloodPressureSecondValue, int heartRate) {
        super(height, weight, (short) bloodPressureFirstValue, (short) bloodPressureSecondValue, (short) heartRate);
        this.date = date;
    }

    //Constructor for WelfareTable
    public DiaryEvaluationUIModel(LocalDateTime date, Welfare welfare){
        this.date = date;
        this.welfare = welfare;
        this.clock = date;

    }

    public DiaryEvaluationUIModel(LocalDate localDate){
        this.date = localDate.atStartOfDay();
    }
    public void addSubentries(DiaryEvaluationUIModel s){
        subEntries.add(s);
    }
    public ArrayList<DiaryEvaluationUIModel> getSubEntries(){
        return subEntries;
    }
    public LocalDate getDate() {
        return date.toLocalDate();
    }
    public LocalDateTime getClock(){return clock;}


    public Symptom getSymptom() {
        return symptom;
    }
    public Welfare getWelfare() {
        return welfare;
    }

}
