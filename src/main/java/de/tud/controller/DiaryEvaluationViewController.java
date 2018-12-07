package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;
import de.tud.view.DiaryEvaluation.DiaryEvaluationView;
import de.tud.view.DiaryEvaluation.SymptomEvaluationView;
import de.tud.view.DiaryEvaluation.WelfareEvaluationView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DiaryEvaluationViewController {

    private DiaryEvaluationView diaryEvaluationView;
    private SymptomEvaluationView symptomEvaluationView;
    private WelfareEvaluationView welfareEvaluationView;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


    private DiaryManager diaryManager = new DiaryManager();
    long diaryId = diaryManager.read().iterator().next().getId();
    Set<DiaryEntry> set;


    public DiaryEvaluationViewController(DiaryEvaluationView diaryEvaluationView) {
        this.diaryEvaluationView = diaryEvaluationView;
        initSymptomTable();

    }

    public void initSymptomTable() {
        symptomEvaluationView = new SymptomEvaluationView();

        ArrayList<SymptomEvaluationView.SymptomTable> symptomTableItems = new ArrayList<>();
        HashSet<String> symptomsForComboBox = new HashSet<>();
        String symptom;

        set = diaryManager.readDiaryEntriesByDiary(diaryId);
        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                for (Symptom s : diaryEntry.getSymptom()) {
                    symptom = s.toString().substring(0, s.toString().indexOf(":"));
                    symptomsForComboBox.add(symptom);

                    symptomTableItems.add(symptomEvaluationView.new SymptomTable(diaryEntry.getDate().format(formatter), s));
                }
            }
            symptomEvaluationView.getGrid().setItems(symptomTableItems);
            symptomEvaluationView.getFilterComboBox().setItems(symptomsForComboBox);
        }

        ListDataProvider<SymptomEvaluationView.SymptomTable> dataProvider =
                (ListDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();

        symptomEvaluationView.getFilterComboBox().addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (valueChangeEvent.getValue() == null || valueChangeEvent.getValue().equals("")) {
                    symptomEvaluationView.getGrid().setItems(symptomTableItems);
                    return;
                }
                ListDataProvider<SymptomEvaluationView.SymptomTable> dataProvider =
                        (ListDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();
                dataProvider.setFilter(SymptomEvaluationView.SymptomTable::getSymptom, s -> s.toString().contains(valueChangeEvent.getValue()));
            }
        });
        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 2) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(1));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(symptomEvaluationView.getViewComponent());

    }

    private void initVitalDataTable() {

        ArrayList<VitalDataTable> vitalDataTableItems = new ArrayList<>();


        if (set != null) {
            for (DiaryEntry diaryEntry : set) {

                if(diaryEntry.getVitalData() == null) {
                    System.out.println(diaryEntry.getVitalData());
                /*
                if (vitalData != null) {
                    LocalDateTime dateTime = diaryEntry.getDate();
                    float height = vitalData.getHeight();
                    float weight = vitalData.getWeight();
                    int bloodPressureFirstValue = vitalData.getBloodPressureFirstValue();
                    int bloodPressureSecondValue = vitalData.getBloodPressureSecondValue();
                    int heartRate = vitalData.getHeartRate();
                    System.out.println(dateTime+" "+height+" "+weight+" "+bloodPressureFirstValue+" "+bloodPressureSecondValue+
                            " "+heartRate);
                    //vitalDataTableItems.add(new VitalDataTable());
                }

            */
                }
                System.out.println(diaryEntry.getVitalData().getWeight());
            }

                //grid.setItems(vitalDataTableItems);
            }


        }
        private void initWelfareTable () {
            welfareEvaluationView = new WelfareEvaluationView();

            ArrayList<WelfareEvaluationView.WelfareTable> welfareTableItems = new ArrayList<>();
            set = diaryManager.readDiaryEntriesByDiary(diaryId);
            if (set != null) {
                for (DiaryEntry diaryEntry : set) {
                    for (Welfare welfare : diaryEntry.getWelfare()) {
                        System.out.println(welfare);
                        welfareTableItems.add(welfareEvaluationView.new WelfareTable(diaryEntry.getDate(), welfare));
                    }
                }
                welfareEvaluationView.getGrid().setItems(welfareTableItems);
            }

            if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 2) {
                diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(1));
            }
            diaryEvaluationView.getVerticalLayout().addComponent(welfareEvaluationView.getViewComponent());
        }

        public void addClickListenerForSymptomButton () {
            diaryEvaluationView.getSymptomTableButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    initSymptomTable();
                }
            });
        }
        public void addClickListenerForVitalDataButton () {
            diaryEvaluationView.getVitalDataTableButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    initVitalDataTable();
                }
            });
        }
        public void addClickListenerForWelfareButton () {
            diaryEvaluationView.getWelfareTableButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    initWelfareTable();
                }
            });

        }


        public class VitalDataTable extends VitalData {
            LocalDateTime dateTime;

            public VitalDataTable(LocalDateTime dateTime, float height, float weight, int bloodPressureFirstValue,
                                  int bloodPressureSecondValue, int heartRate) {
                super();
                this.dateTime = dateTime;
            }

            public LocalDateTime getDateTime() {
                return dateTime;
            }
        }


    }

