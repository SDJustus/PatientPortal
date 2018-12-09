package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.dd.acceptcriteria.Not;
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
import de.tud.view.VitalData.VitalDataView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DiaryEvaluationViewController {

    private DiaryEvaluationView diaryEvaluationView;
    private SymptomEvaluationView symptomEvaluationView;

    private WelfareEvaluationView welfareEvaluationView;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static DiaryManager diaryManager = DiaryManager.getInstance();
    static long diaryId = diaryManager.read().iterator().next().getId();
    static Set<DiaryEntry> set = diaryManager.readDiaryEntriesByDiary(diaryId);

    public DiaryEvaluationViewController(DiaryEvaluationView diaryEvaluationView) {
        this.diaryEvaluationView = diaryEvaluationView;
        initSymptomTable();

    }
    public void initSymptomTable() {
        symptomEvaluationView = new SymptomEvaluationView();

        ArrayList<SymptomEvaluationView.SymptomTable> symptomTableItems = new ArrayList<>();
        HashSet<String> symptomsForComboBox = new HashSet<>();
        String symptom;

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


        symptomEvaluationView.getFilterComboBox().addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (valueChangeEvent.getValue() == null || valueChangeEvent.getValue().equals("")) {
                    symptomEvaluationView.getGrid().setItems(symptomTableItems);
                    return;
                }
                ListDataProvider<SymptomEvaluationView.SymptomTable> dataProvider2 =
                        (ListDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();

                //TODO: so funktioniert es erstmal
                if(symptomEvaluationView.getFromDate().getValue() != null && symptomEvaluationView.getToDate().getValue() != null){


                    LocalDateTime fromDate = symptomEvaluationView.getFromDate().getValue();
                    LocalDateTime toDate = symptomEvaluationView.getToDate().getValue();
                    dataProvider2.setFilter(SymptomEvaluationView.SymptomTable::getDate, s ->LocalDateTime.parse(s, formatter).isAfter(fromDate) && LocalDateTime.parse(s, formatter).isBefore(toDate));
                }
                ListDataProvider<SymptomEvaluationView.SymptomTable> dataProvider =
                        (ListDataProvider<SymptomEvaluationView.SymptomTable>) ((ListDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider());

                dataProvider.addFilter(SymptomEvaluationView.SymptomTable::getSymptom, s -> s.toString().contains(valueChangeEvent.getValue()));




            }
        });

                /*
        symptomEvaluationView.getToDate().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
                LocalDateTime fromDate = symptomEvaluationView.getFromDate().getValue();


                if(fromDate == null || valueChangeEvent.getValue() == null){
                    symptomEvaluationView.getGrid().setItems(symptomTableItems);
                    return;
                }

                if(fromDate == null){
                    Notification.show("Start-Datum fehlt!");
                    symptomEvaluationView.getToDate().getErrorMessage();
                    return;
                }
                if(valueChangeEvent.getValue().isBefore(fromDate)){
                    Notification.show("Start-Datum liegt nach dem Ziel-Datum!");
                    return;
                }

                ListDataProvider<SymptomEvaluationView.SymptomTable> dataProvider = (ListDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();
                //dataProvider.setFilter(SymptomEvaluationView.SymptomTable::getDate, s ->LocalDateTime.parse(s, formatter).isAfter(fromDate) && LocalDateTime.parse(s, formatter).isBefore(valueChangeEvent.getValue()));
                dataProvider.setFilter(SymptomEvaluationView.SymptomTable::getDate, s ->LocalDateTime.parse(s, formatter).isAfter(fromDate) && LocalDateTime.parse(s, formatter).isBefore(valueChangeEvent.getValue()));

                if(symptomEvaluationView.getFilterComboBox().getValue() != null){
                    ListDataProvider<SymptomEvaluationView.SymptomTable> dataProvider2 =
                            (ListDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();
                    symptomEvaluationView.getGrid().setDataProvider(dataProvider2);

                    dataProvider2.setFilter(SymptomEvaluationView.SymptomTable::getSymptom, s -> s.toString().contains(symptomEvaluationView.getFilterComboBox().getValue()));
                }


            }
        });
        */

        symptomEvaluationView.getResetButton().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                symptomEvaluationView.getToDate().clear();
                symptomEvaluationView.getFromDate().clear();
                symptomEvaluationView.getFilterComboBox().clear();
            }
        });


        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 2) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(1));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(symptomEvaluationView.getViewComponent());

    }
    private void initVitalDataTable() {


       
        if (set != null) {
            for (DiaryEntry diaryEntry : set) {

                if (diaryEntry.getVitalData() != null) {
                    System.out.println(diaryEntry.getVitalData().getId().toString());
                }



            }

            //grid.setItems(vitalDataTableItems);
        }
        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 2) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(1));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(symptomEvaluationView.getViewComponent());
    }
    private void initWelfareTable () {
        welfareEvaluationView = new WelfareEvaluationView();

            ArrayList<WelfareEvaluationView.WelfareTable> welfareTableItems = new ArrayList<>();
            HashSet<String> welfareForComboBox = new HashSet<>();

            if (set != null) {
                for (DiaryEntry diaryEntry : set) {
                    for (Welfare welfare : diaryEntry.getWelfare()) {
                        String w = welfare.toString().substring(0, welfare.toString().indexOf(":"));
                        welfareTableItems.add(welfareEvaluationView.new WelfareTable(diaryEntry.getDate().format(formatter), welfare));
                        welfareForComboBox.add(w);
                    }
                }
                welfareEvaluationView.getFilterComboBox().setItems(welfareForComboBox);
                welfareEvaluationView.getGrid().setItems(welfareTableItems);
            }

            welfareEvaluationView.getFilterComboBox().addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (valueChangeEvent.getValue() == null || valueChangeEvent.getValue().equals("")) {
                    welfareEvaluationView.getGrid().setItems(welfareTableItems);
                    return;
                }
                ListDataProvider<WelfareEvaluationView.WelfareTable> dataProvider =
                        (ListDataProvider<WelfareEvaluationView.WelfareTable>) welfareEvaluationView.getGrid().getDataProvider();
                dataProvider.setFilter(WelfareEvaluationView.WelfareTable::getWelfare, s -> s.toString().contains(valueChangeEvent.getValue()));
            }
        });

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





    }

