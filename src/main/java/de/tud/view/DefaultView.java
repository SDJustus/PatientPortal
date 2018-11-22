package de.tud.view;

import com.vaadin.data.HasValue;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import com.vaadin.navigator.View;
import java.time.LocalDateTime;
import java.util.*;
import com.vaadin.server.*;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.DiaryEntryTableViewAdapter;
import de.tud.model.VitalDataSet;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;


public class DefaultView extends Composite implements View {
    private VerticalLayout verticalLayoutMain = new VerticalLayout(); //Seitenstruktur
    private HorizontalLayout menuBar = new HorizontalLayout();
    public static VerticalLayout verticalLayout = new VerticalLayout();  //Vertical Layout wird in horizontal Layout gepackt
    private DateTimeField dateTimeField = new DateTimeField(); //wird in Vertical Layout gepackt
    //com.vaadin.ui.Label label = new com.vaadin.ui.Label("Das ist die Startseite des Patientenportals.");
    private Label label;
    private static Button save = new Button("Speichern");
    private Button newDiaryEntry;
    private static List<String> symptomList;
    public static HashSet<String> avoidDuplicateSymptomsSet = new HashSet<>();   //Eingabe von doppelten Symptomen vermeiden
    private static List<SymptomSelectionViewFactory> symptomSelectionViewFactories;

    private List<DiaryEntryTableViewAdapter> tagebuch = new ArrayList<>();

    public DefaultView(){
        UI.getCurrent().getPage().getStyles().add("#smileybild:hover{transform: scale(1.2);}"+
                "#smileybild:{transition: transform .2s;}+" +
                ".v-panel{padding-bottom: 80px;}"+
                "#header-label{font-weight: bold; font-size:40px;}");
        label = new Label("Neuer Tagebucheintrag");
        label.setId("header-label");

        newDiaryEntry = new Button("Eintrag verwerfen", VaadinIcons.TRASH);

        //save Button ausschalten standardmäßig
        save.setEnabled(false);
        //DatePicker
        DateTimeField dateTimeField = new DateTimeField();
        //Integritätsbedingungen für das Datum
        dateTimeField.setRangeStart(LocalDateTime.now().minusWeeks(3));
        dateTimeField.setRangeEnd(LocalDateTime.now());
        dateTimeField.addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
                if(!dateTimeField.getValue().equals("") && checkSymptomSelection()){
                    save.setEnabled(true);
                }
            }
        });


        dateTimeField.addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
              //TODO
            }
        });




        menuBar.addComponents(dateTimeField,save, newDiaryEntry);

        verticalLayout.setSizeUndefined();
        verticalLayout.setSpacing(true);

        //Symptome erstellen
        createSymptomList();

        //GridLayout hinzufügen
        SymptomSelectionViewFactory symptomSelectionViewFactory = new SymptomSelectionViewFactory();
        verticalLayout.addComponent(symptomSelectionViewFactory.getSymptomSelectionView());
        symptomSelectionViewFactories = new ArrayList<>();
        symptomSelectionViewFactories.add(symptomSelectionViewFactory);

        Panel panel = new Panel();
        panel.setContent(verticalLayout);


        panel.setHeight(""+0.8*Page.getCurrent().getBrowserWindowHeight());
        panel.setWidth(""+-200+Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            panel.setHeight(""+0.8*e.getHeight());
            panel.setWidth(""+-200+e.getWidth());
        });


        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(dateTimeField.getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    save.setEnabled(false);
                    return;
                }

                for(SymptomSelectionViewFactory s: symptomSelectionViewFactories){
                    avoidDuplicateSymptomsSet.add(s.getSelectedSymptom());
                }

                if(DefaultView.avoidDuplicateSymptomsSet.size() < DefaultView.verticalLayout.getComponentCount()){
                    Notification.show("Symptome dürfen nur einmal angegeben werden!");
                    save.setEnabled(false);
                    return;
                }

                HashSet<Symptom> symptoms = new HashSet<>();
                for(SymptomSelectionViewFactory s: symptomSelectionViewFactories){
                    System.out.println(s.getSymptomName() + " "+s.getSelection());
                    symptoms.add(s.getSymptomArt());
                }
                saveDiaryEntry(dateTimeField.getValue(), symptoms);
                Notification.show("Eintrag erfolgreich gespeichert");

            }
        });

        newDiaryEntry.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(symptomSelectionViewFactories.size() > 1) {
                        Notification.show("Die Einträge werden verworfen");
                        //TODO: Dialog Window with Yes or No

                }
                verticalLayout.removeAllComponents();
                symptomSelectionViewFactories.clear();
                symptomList.clear();
                createSymptomList();
                verticalLayout.addComponent(new SymptomSelectionViewFactory().getSymptomSelectionView());
            }
        });


        verticalLayoutMain.addComponents(label,menuBar,panel);
        setCompositionRoot(verticalLayoutMain);
    }

    public static void setSaveButtonEnabled(boolean value){
        save.setEnabled(value);
    }


    public static void addNewSymptom(SymptomSelectionViewFactory symptomSelectionViewFactory){
        verticalLayout.addComponents(symptomSelectionViewFactory.getSymptomSelectionView());
        symptomSelectionViewFactories.add(symptomSelectionViewFactory);
    }


    private void createSymptomList(){
        this.symptomList = new ArrayList<>();
        symptomList.add("Müdigkeit");
        symptomList.add("Gehstörung");
        symptomList.add("Kognitive Störung");
        symptomList.add("Schmerzen");
        symptomList.add("Blasenstörung");
        symptomList.add("Depression");
        symptomList.add("Darmstörung");
        symptomList.add("Spastik im linken Arm");
        symptomList.add("Spastik im rechten Arm");
        symptomList.add("Spastik im linken Bein");
        symptomList.add("Spastik im rechten Bein");
    }
    public static List<String> getSymptomsList(){
        return symptomList;
    }

    public void saveDiaryEntry(LocalDateTime datum, Set<Symptom> symptoms){
        DiaryManager diaryManager = new DiaryManager();
        //diaryManager.addDiary(new Diary());
        Diary diary = diaryManager.read().get(0);
        long diaryId = diary.getId();

        DiaryEntry diaryEntry = new DiaryEntry(datum, symptoms, new VitalDataSet());    //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        DiaryManager.getInstance().addDiaryEntry(diaryEntry, diaryId);

    }
    public boolean checkSymptomSelection(){
        List<Boolean> list = new ArrayList<>();

        for(SymptomSelectionViewFactory s: symptomSelectionViewFactories){
            list.add(s.getSelection() == null);
        }
        if(list.contains(true)){
            return false;
        }
        return true;
    }




}