package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;
import de.tud.view.DiaryEvaluation.*;
import de.tud.view.VitalData.VitalDataView;

import java.time.LocalDate;
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
    private VitalDataEvaluationView vitalDataEvaluationView;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("YYYY-MM-DD");
    private static DiaryManager diaryManager = DiaryManager.getInstance();
    static long diaryId = diaryManager.read().iterator().next().getId();
    public static Set<DiaryEntry> set = diaryManager.readDiaryEntriesByDiary(diaryId);

    public DiaryEvaluationViewController(DiaryEvaluationView diaryEvaluationView) {
        this.diaryEvaluationView = diaryEvaluationView;
        initSymptomTable();

    }
    public void initSymptomTable() {
        symptomEvaluationView = new SymptomEvaluationView();

        ArrayList<SymptomEvaluationView.SymptomTable> symptomTableItems = new ArrayList<>();


        HashSet<String> symptomsForComboBox = new HashSet<>();
        String symptom;
        HashSet<LocalDate> localDate = new HashSet<>();
        ArrayList<SymptomEvaluationView.SymptomTable> superEntryList = new ArrayList<>();



        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                for (Symptom s : diaryEntry.getSymptom()) {
                    symptom = s.toString().substring(0, s.toString().indexOf(":"));
                    symptomsForComboBox.add(symptom);

                    localDate.add(diaryEntry.getDate().toLocalDate());
                    symptomTableItems.add(symptomEvaluationView.new SymptomTable(diaryEntry.getDate(), s));

                }
            }

            for (LocalDate localDate1:localDate){
                SymptomEvaluationView.SymptomTable superEntry = symptomEvaluationView.new SymptomTable(localDate1);
                superEntryList.add(superEntry);

                for(SymptomEvaluationView.SymptomTable s : symptomTableItems){
                    if(s.getDate().equals(localDate1)) {
                        superEntry.addSubentries(s);
                    }
                }
            }


            symptomEvaluationView.getGrid().setItems(superEntryList, SymptomEvaluationView.SymptomTable::getSubEntries);
            symptomEvaluationView.getFilterComboBox().setItems(symptomsForComboBox);
        }


        symptomEvaluationView.getFilterComboBox().addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (valueChangeEvent.getValue() == null || valueChangeEvent.getValue().equals("")) {
                    symptomEvaluationView.getGrid().setItems(superEntryList, SymptomEvaluationView.SymptomTable::getSubEntries);
                    return;
                }
                symptomEvaluationView.getGrid().setItems(symptomTableItems);

                TreeDataProvider<SymptomEvaluationView.SymptomTable> dataProvider2 =
                        (TreeDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();

                //TODO: so funktioniert es erstmal
                if(symptomEvaluationView.getFromDate().getValue() != null && symptomEvaluationView.getToDate().getValue() != null){

                    LocalDate fromDate = symptomEvaluationView.getFromDate().getValue();
                    LocalDate toDate = symptomEvaluationView.getToDate().getValue();
                    //TODO auskommentieren
                    dataProvider2.setFilter(SymptomEvaluationView.SymptomTable::getDate, s -> s.isAfter(fromDate.minusDays(1)) && s.isBefore(toDate.plusDays(1)));
                }
                TreeDataProvider<SymptomEvaluationView.SymptomTable> dataProvider =
                        (TreeDataProvider<SymptomEvaluationView.SymptomTable>) (TreeDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();

                dataProvider.addFilter(SymptomEvaluationView.SymptomTable::getSymptom, s -> s.toString().contains(valueChangeEvent.getValue()));

            }
        });

        symptomEvaluationView.getToDate().addValueChangeListener(new HasValue.ValueChangeListener<LocalDate>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {
                LocalDate fromDate = symptomEvaluationView.getFromDate().getValue();


                if(fromDate == null || valueChangeEvent.getValue() == null){
                    symptomEvaluationView.getGrid().setItems(superEntryList, SymptomEvaluationView.SymptomTable::getSubEntries);
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

                TreeDataProvider<SymptomEvaluationView.SymptomTable> dataProvider = (TreeDataProvider<SymptomEvaluationView.SymptomTable>) symptomEvaluationView.getGrid().getDataProvider();
                //dataProvider.setFilter(SymptomEvaluationView.SymptomTable::getDate, s ->LocalDateTime.parse(s, formatter).isAfter(fromDate) && LocalDateTime.parse(s, formatter).isBefore(valueChangeEvent.getValue()));

                //TODO auskommentieren
                dataProvider.setFilter(SymptomEvaluationView.SymptomTable::getDate, s ->s.isAfter(fromDate.minusDays(1)) && s.isBefore(valueChangeEvent.getValue().plusDays(1)));
            }
        });


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
        vitalDataEvaluationView = new VitalDataEvaluationView();

        ArrayList<VitalDataEvaluationView.VitalDataTable> vitaDataTableItems = new ArrayList<>();

        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                if (diaryEntry.getVitalData() != null) {
                    VitalData vd = diaryEntry.getVitalData();
                   vitaDataTableItems.add(vitalDataEvaluationView.new VitalDataTable(diaryEntry.getDate().format(formatter), vd.getHeight(),
                           vd.getWeight(), vd.getBloodPressureFirstValue(), vd.getBloodPressureSecondValue(), vd.getHeartRate()));
                }

            }
            vitalDataEvaluationView.getGrid().setItems(vitaDataTableItems);
        }


        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 2) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(1));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(vitalDataEvaluationView.getViewComponent());
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
                    activateButtons();
                    diaryEvaluationView.getSymptomTableButton().setEnabled(false);
                    initSymptomTable();
                }
            });
        }
    public void addClickListenerForVitalDataButton () {
            diaryEvaluationView.getVitalDataTableButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    activateButtons();
                    diaryEvaluationView.getVitalDataTableButton().setEnabled(false);
                    initVitalDataTable();
                }
            });
        }
    public void addClickListenerForWelfareButton () {
            diaryEvaluationView.getWelfareTableButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    activateButtons();
                    diaryEvaluationView.getWelfareTableButton().setEnabled(false);
                    initWelfareTable();
                }
            });

        }
    private void activateButtons(){
        diaryEvaluationView.getVitalDataTableButton().setEnabled(true);
        diaryEvaluationView.getWelfareTableButton().setEnabled(true);
        diaryEvaluationView.getSymptomTableButton().setEnabled(true);
    }
    //private void filterLogicForGrid(EvaluationView view, ArrayList<?> superEntries, ArrayList<>){

}






