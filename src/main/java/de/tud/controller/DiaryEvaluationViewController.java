package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.TreeDataProvider;

import com.vaadin.ui.*;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;
import de.tud.view.DiaryEvaluation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class DiaryEvaluationViewController {

    private DiaryEvaluationView diaryEvaluationView;
    private SymptomEvaluationView symptomEvaluationView;
    private WelfareEvaluationView welfareEvaluationView;
    private VitalDataEvaluationView vitalDataEvaluationView;
    private static DiaryManager diaryManager = DiaryManager.getInstance();
    static long diaryId = diaryManager.read().iterator().next().getId();
    public static Set<DiaryEntry> set = diaryManager.readDiaryEntriesByDiary(diaryId);

    public DiaryEvaluationViewController(DiaryEvaluationView diaryEvaluationView) {
        this.diaryEvaluationView = diaryEvaluationView;
        initSymptomTable();

    }
    private void initSymptomTable() {
        symptomEvaluationView = new SymptomEvaluationView();

        ArrayList<DiaryEvaluationUIModel> symptomTableItems = new ArrayList<>();
        HashSet<String> symptomsForComboBox = new HashSet<>();
        String symptom;
        HashSet<LocalDate> localDate = new HashSet<>();
        ArrayList<DiaryEvaluationUIModel> superEntryList = new ArrayList<>();


        if (DiaryManager.getInstance().readDiaryEntriesByDiary(diaryId) != null) {
            for (DiaryEntry diaryEntry : set) {
                for (Symptom s : diaryEntry.getSymptom()) {
                    symptom = s.toString().substring(0, s.toString().indexOf(":"));
                    symptomsForComboBox.add(symptom);

                    localDate.add(diaryEntry.getDate().toLocalDate());
                    symptomTableItems.add(new DiaryEvaluationUIModel(diaryEntry.getDate(), s));

                }
            }
            generateSubEntries(localDate, superEntryList, symptomTableItems);

            initTableFilters("Symptome", symptomEvaluationView, superEntryList, symptomTableItems);

            symptomEvaluationView.getGrid().setItems(superEntryList, DiaryEvaluationUIModel::getSubEntries);
            symptomEvaluationView.getFilterComboBox().setItems(symptomsForComboBox);
        }



        symptomEvaluationView.getResetButton().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                symptomEvaluationView.getToDate().clear();
                symptomEvaluationView.getFromDate().clear();
                symptomEvaluationView.getFilterComboBox().clear();
            }
        });


        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 3) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(2));
        }


        diaryEvaluationView.getVerticalLayout().addComponent(symptomEvaluationView.getViewComponent());

    }
    private void initVitalDataTable() {
        vitalDataEvaluationView = new VitalDataEvaluationView();

        ArrayList<DiaryEvaluationUIModel> vitaDataTableItems = new ArrayList<>();


        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                if (diaryEntry.getVitalData() != null) {
                    VitalData vd = diaryEntry.getVitalData();
                   vitaDataTableItems.add(new DiaryEvaluationUIModel(diaryEntry.getDate(), vd.getHeight(),
                           vd.getWeight(), vd.getBloodPressureFirstValue(), vd.getBloodPressureSecondValue(), vd.getHeartRate()));

                }

            }

            vitalDataEvaluationView.getGrid().setItems(vitaDataTableItems);
        }

        initTableFilters("Vitaldaten", vitalDataEvaluationView,null, vitaDataTableItems);


        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 3) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(2));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(vitalDataEvaluationView.getViewComponent());
    }
    private void initWelfareTable () {
            welfareEvaluationView = new WelfareEvaluationView();

            ArrayList<DiaryEvaluationUIModel> welfareTableItems = new ArrayList<>();
            HashSet<String> welfareForComboBox = new HashSet<>();
            HashSet<LocalDate> localDate = new HashSet<>();
            ArrayList<DiaryEvaluationUIModel> superEntryList = new ArrayList<>();


            if (set != null) {
                for (DiaryEntry diaryEntry : set) {
                    for (Welfare welfare : diaryEntry.getWelfare()) {
                        String w = welfare.toString().substring(0, welfare.toString().indexOf(":"));
                        welfareTableItems.add(new DiaryEvaluationUIModel(diaryEntry.getDate(), welfare));
                        welfareForComboBox.add(w);
                        localDate.add(diaryEntry.getDate().toLocalDate());
                    }
                }
                generateSubEntries(localDate, superEntryList, welfareTableItems);

                welfareEvaluationView.getFilterComboBox().setItems(welfareForComboBox);


                welfareEvaluationView.getGrid().setItems(superEntryList, DiaryEvaluationUIModel::getSubEntries);

            }


            initTableFilters("Wohlbefinden", welfareEvaluationView, superEntryList, welfareTableItems);


            if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 3 ) {
                diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(2));
            }
            diaryEvaluationView.getVerticalLayout().addComponent(welfareEvaluationView.getViewComponent());
        }

    private void initSymptomChart () {

        SymptomChartView symp = new SymptomChartView();


        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 3 ) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(2));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(symp.setup());
    }
    private void initWelfareChart () {

        WelfareChartView welfareChartView = new WelfareChartView();


        if (diaryEvaluationView.getVerticalLayout().getComponentCount() == 3 ) {
            diaryEvaluationView.getVerticalLayout().removeComponent(diaryEvaluationView.getVerticalLayout().getComponent(2));
        }
        diaryEvaluationView.getVerticalLayout().addComponent(welfareChartView.setup());
    }



    public void addClickListenerForWelfareChartButton()
    {
        diaryEvaluationView.getWelfareChartButton().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                activateButtons();
                diaryEvaluationView.getWelfareChartButton().setEnabled(false);
                initSymptomChart();
            }
        });


    }




    public void addClickListenerForSymptomButton () {
            diaryEvaluationView.getSymptomTableButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    set = DiaryManager.getInstance().readDiaryEntriesByDiary(diaryId);
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
                    set = DiaryManager.getInstance().readDiaryEntriesByDiary(diaryId);
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
                    set = DiaryManager.getInstance().readDiaryEntriesByDiary(diaryId);
                    activateButtons();
                    diaryEvaluationView.getWelfareTableButton().setEnabled(false);
                    initWelfareTable();
                }
            });

        }

        public void addClickListenerForSymptomChartButton()
        {
            diaryEvaluationView.getSymptomChartButton().addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    activateButtons();
                    diaryEvaluationView.getSymptomChartButton().setEnabled(false);
                    initSymptomChart();
                }
            });


        }

    private void activateButtons(){
        diaryEvaluationView.getVitalDataTableButton().setEnabled(true);
        diaryEvaluationView.getWelfareTableButton().setEnabled(true);
        diaryEvaluationView.getSymptomTableButton().setEnabled(true);
        diaryEvaluationView.getWelfareChartButton().setEnabled(true);
        diaryEvaluationView.getSymptomChartButton().setEnabled(true);
    }

    private void initTableFilters(String filterCriteriaForCombobox, EvaluationView view, ArrayList<DiaryEvaluationUIModel> superEntryList,
                                   ArrayList<DiaryEvaluationUIModel> gridItems){

        view.getFilterComboBox().addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (valueChangeEvent.getValue() == null || valueChangeEvent.getValue().equals("")) {
                    if(filterCriteriaForCombobox.equals("Symptome") || filterCriteriaForCombobox.equals("Wohlbefinden")){
                        view.getGrid().setItems(superEntryList, DiaryEvaluationUIModel::getSubEntries);
                        return;
                    }
                    view.getGrid().setItems(gridItems);
                    return;
                }
                view.getGrid().setItems(gridItems);

                TreeDataProvider<DiaryEvaluationUIModel> dataProvider2 =
                        (TreeDataProvider<DiaryEvaluationUIModel>) view.getGrid().getDataProvider();

                //TODO: so funktioniert es erstmal
                if(view.getFromDate().getValue() != null && view.getToDate().getValue() != null){

                    LocalDate fromDate = view.getFromDate().getValue();
                    LocalDate toDate = view.getToDate().getValue();
                    //TODO auskommentieren
                    dataProvider2.setFilter(DiaryEvaluationUIModel::getDate, s -> s.isAfter(fromDate.minusDays(1)) && s.isBefore(toDate.plusDays(1)));
                }
                if(filterCriteriaForCombobox.equals("Symptome")) {
                    TreeDataProvider<DiaryEvaluationUIModel> dataProvider =
                            (TreeDataProvider<DiaryEvaluationUIModel>) (TreeDataProvider<DiaryEvaluationUIModel>)
                                    view.getGrid().getDataProvider();
                    dataProvider.addFilter(DiaryEvaluationUIModel::getSymptom, s -> s.toString().contains(valueChangeEvent.getValue()));
                }
                if(filterCriteriaForCombobox.equals("Wohlbefinden")){
                    TreeDataProvider<DiaryEvaluationUIModel> dataProvider =
                            (TreeDataProvider<DiaryEvaluationUIModel>) (TreeDataProvider<DiaryEvaluationUIModel>)
                                    view.getGrid().getDataProvider();
                    dataProvider.addFilter(DiaryEvaluationUIModel::getWelfare, s -> s.toString().contains(valueChangeEvent.getValue()));
                }
            }
        });

        view.getToDate().addValueChangeListener(new HasValue.ValueChangeListener<LocalDate>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {
                LocalDate fromDate = view.getFromDate().getValue();


                if(fromDate == null || valueChangeEvent.getValue() == null){
                    if(filterCriteriaForCombobox.equals("Symptome") || filterCriteriaForCombobox.equals("Wohlbefinden")){
                        view.getGrid().setItems(superEntryList, DiaryEvaluationUIModel::getSubEntries);
                        return;
                    }
                    view.getGrid().setItems(gridItems);
                    return;
                }

                if(fromDate == null){
                    Notification.show("Start-Datum fehlt!");
                    view.getToDate().getErrorMessage();
                    return;
                }
                if(valueChangeEvent.getValue().isBefore(fromDate)){
                    Notification.show("Start-Datum liegt nach dem Ziel-Datum!");
                    return;
                }

                TreeDataProvider<DiaryEvaluationUIModel> dataProvider = (TreeDataProvider<DiaryEvaluationUIModel>) view.getGrid().getDataProvider();
                //dataProvider.setFilter(SymptomEvaluationView.SymptomTable::getDate, s ->LocalDateTime.parse(s, formatter).isAfter(fromDate) && LocalDateTime.parse(s, formatter).isBefore(valueChangeEvent.getValue()));

                //TODO auskommentieren
                dataProvider.setFilter(DiaryEvaluationUIModel::getDate, s ->s.isAfter(fromDate.minusDays(1)) && s.isBefore(valueChangeEvent.getValue().plusDays(1)));

            }
        });


        view.getResetButton().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                view.getToDate().clear();
                view.getFromDate().clear();
                view.getFilterComboBox().clear();
            }
        });
    }


    private void generateSubEntries(HashSet<LocalDate> localDateHashSet, ArrayList<DiaryEvaluationUIModel> superEntryList,
                                    ArrayList<DiaryEvaluationUIModel>  tableItems ){
        for (LocalDate localDate1:localDateHashSet){
            DiaryEvaluationUIModel superEntry = new DiaryEvaluationUIModel(localDate1);
            superEntryList.add(superEntry);

            for(DiaryEvaluationUIModel s : tableItems){
                if(s.getDate().equals(localDate1)) {
                    superEntry.addSubentries(s);
                }
            }
        }

    }




}






