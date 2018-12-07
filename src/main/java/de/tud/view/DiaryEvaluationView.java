package de.tud.view;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.model.DiaryEntry;
import de.tud.model.DiaryEntryTableViewAdapter;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DiaryEvaluationView extends Composite implements View {
    private VerticalLayout verticalLayout = new VerticalLayout();
    private Button symptomTableButton = new Button("Symptome");
    private Button vitalDataTableButton = new Button("Vitaldaten");
    private Button welfareTableButton = new Button("Wohlbefinden");
    private HorizontalLayout menuBar = new HorizontalLayout();
    private DiaryManager diaryManager = new DiaryManager();
    long diaryId = diaryManager.read().iterator().next().getId();
    Set<DiaryEntry> set;


    public DiaryEvaluationView() {
        HorizontalLayout menuBar = new HorizontalLayout();


        menuBar.addComponents(symptomTableButton, vitalDataTableButton, welfareTableButton);
        verticalLayout.addComponents(menuBar);


        symptomTable();
        addClickListenerFortButtons();
        setCompositionRoot(verticalLayout);
    }

    private void symptomTable() {
        ComboBox<String> symptomFilterComboBox = new ComboBox<>();

        VerticalLayout tableContainer = new VerticalLayout();
        Panel panel = new Panel();
        Grid<SymptomTable> grid = new Grid<>();
        grid.addColumn(SymptomTable::getDate).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.setResponsive(true);
        grid.setSizeFull();

        grid.addColumn(SymptomTable::getSymptom).setId("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setCaption("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setResizable(false);
        grid.getColumn("Datum").setResizable(false);


        //ComboBox

        symptomFilterComboBox.setPlaceholder("Symptom auswählen");

        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(200)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(350)));
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(200)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(350)));

        });


        ArrayList<SymptomTable> symptomTableItems = new ArrayList<>();
        HashSet<String> symptomsForComboBox = new HashSet<>();
        String symptom;

        set = diaryManager.readDiaryEntriesByDiary(diaryId);
        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                for (Symptom s : diaryEntry.getSymptom()) {
                    symptom = s.toString().substring(0, s.toString().indexOf(":"));
                    symptomsForComboBox.add(symptom);
                    symptomTableItems.add(new SymptomTable(diaryEntry.getDate(), s));
                }
            }

            grid.setItems(symptomTableItems);
            symptomFilterComboBox.setItems(symptomsForComboBox);
        }

        ListDataProvider<SymptomTable> dataProvider =
                (ListDataProvider<SymptomTable>) grid.getDataProvider();


        symptomFilterComboBox.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (valueChangeEvent.getValue() == null || valueChangeEvent.getValue().equals("")) {
                    grid.setItems(symptomTableItems);
                    return;
                }
                ListDataProvider<SymptomTable> dataProvider =
                        (ListDataProvider<SymptomTable>) grid.getDataProvider();
                dataProvider.setFilter(SymptomTable::getSymptom, s -> s.toString().contains(valueChangeEvent.getValue()));


            }
        });
        panel.setContent(grid);

        tableContainer.addComponents(symptomFilterComboBox, panel);
        verticalLayout.addComponents(tableContainer);
    }

    private void vitalDataTable() {
        VerticalLayout tableContainer = new VerticalLayout();
        Panel panel = new Panel();
        Grid<VitalDataTable> grid = new Grid<>();
        grid.addColumn(VitalDataTable::getBloodPressureFirstValue).setId("Blutdruck (systolisch)");
        grid.getColumn("Blutdruck (systolisch)").setCaption("Blutdruck (systolisch)");
        grid.getColumn("Blutdruck (systolisch)").setResizable(false);


        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(200)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(300)));


        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(200)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(300)));

        });

        ArrayList<VitalDataTable> vitalDataTableItems = new ArrayList<>();


        set = diaryManager.readDiaryEntriesByDiary(diaryId);
        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                VitalData vitalData = diaryEntry.getVitalData();
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

            }
            //grid.setItems(vitalDataTableItems);
        }

            /*
            panel.setContent(grid);
            panel.setSizeFull();

            tableContainer.addComponents(panel);
            */
    }
    private void welfareTable(){
        set = diaryManager.readDiaryEntriesByDiary(diaryId);
        if (set != null) {
            for (DiaryEntry diaryEntry : set) {
                    for(Welfare welfare : diaryEntry.getWelfare()){
                        System.out.println(welfare);
                    }
                }
            }
        }


    private void addClickListenerFortButtons(){
        symptomTableButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                symptomTable();
            }
        });
        vitalDataTableButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                vitalDataTable();
            }
        });
        welfareTableButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                welfareTable();
            }
        });
    }

        //helperclasses:
        private class SymptomTable {
            private Symptom symptom;
            private LocalDateTime date;

            public SymptomTable(LocalDateTime d, Symptom s) {
                this.symptom = s;
                this.date = d;
            }

            public Symptom getSymptom() {
                return symptom;
            }

            public LocalDateTime getDate() {
                return date;
            }
        }
        private class VitalDataTable extends VitalData {
            LocalDateTime dateTime;
            public VitalDataTable(LocalDateTime dateTime, float height, float weight, int bloodPressureFirstValue,
                                  int bloodPressureSecondValue, int heartRate){
                super();
                this.dateTime = dateTime;
            }
            public LocalDateTime getDateTime(){
                return dateTime;
            }
        }
        private class WelfareTable {
            LocalDateTime dateTime;
            Welfare welfare;
            public WelfareTable(LocalDateTime dateTime, Welfare welfare ){
                this.dateTime  = dateTime;
                this.welfare = welfare;
            }
            public LocalDateTime getDateTime() {
                return dateTime;
            }

            public Welfare getWelfare() {
                return welfare;
            }
        }


}
