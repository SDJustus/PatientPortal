package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.view.symptoms.SymptomView;
import de.tud.view.symptoms.SymptomSelectionView;

import java.time.LocalDateTime;
import java.util.*;

public class SymptomViewController {


    private SymptomView symptomView;
    private HashSet<String> avoidDuplicateSymptomsSet = new HashSet<>();   //Eingabe von doppelten Symptomen vermeiden
    private List<SymptomSelectionViewController> symptomSelectionViewControllers = new ArrayList<>();
    private TreeSet<String> symptomList;
    private int numberOfSymptoms = createSymptomList().size();
    private int counter = 0;


    public SymptomViewController(SymptomView symptomView){
        this.symptomView = symptomView;
        this.symptomList = createSymptomList();

        integrityRestrictionsDateTimeField();
        addNewSymptomSelectionView();
        symptomView.getDateTimeField().setValue(LocalDateTime.now());
    }

    public void addDateTimeFieldChangeListener(){
       symptomView.getDateTimeField().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
                //TODO!

                if(symptomView.getDateTimeField().getValue() != null && checkSymptomSelection() && checkDateIntegrity()) {
                    symptomView.getSave().setEnabled(true);
                }
            }
        });

    }
    public TreeSet<String> getSymptomList(){
        return  symptomList;
    }


    private TreeSet<String> createSymptomList(){
        TreeSet<String> symptomList = new TreeSet<>();
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
        return symptomList;
    }
    public boolean checkDateIntegrity(){
        LocalDateTime rangeEnd = symptomView.getDateTimeField().getRangeEnd();
        LocalDateTime rangeStart = symptomView.getDateTimeField().getRangeStart();
        LocalDateTime date = symptomView.getDateTimeField().getValue();
        if(date.isAfter(rangeEnd) || date.isBefore(rangeStart.minusMinutes(1))){
            Notification.show("unzulässiges Datum gewählt!");
            return false;
        }
        return true;
    }

    public boolean checkSymptomSelection(){
        List<Boolean> list = new ArrayList<>();

        for(SymptomSelectionViewController s: symptomSelectionViewControllers){
            list.add(s.getSelectedSymptom() == null);
        }
        if(list.contains(true)){
            return false;
        }
        return true;
    }

    private void integrityRestrictionsDateTimeField(){
        symptomView.getDateTimeField().setRangeStart(LocalDateTime.now().minusWeeks(3));
        symptomView.getDateTimeField().setRangeEnd(LocalDateTime.now().plusMinutes(1));
    }


    public void addNewSymptomSelectionView(){

        SymptomSelectionView symptomSelectionView = new SymptomSelectionView(this);
        System.out.println(symptomList);
        symptomSelectionViewControllers.add(symptomSelectionView.getSymptomSelectionViewController());
        symptomView.getVerticalLayout().addComponents(symptomSelectionView.getViewComponent());

        counter++;
    }

    public void setSaveButtonEnabled(boolean value) {
        symptomView.getSave().setEnabled(value);
    }

    //TODO Check Save Button Methode
    public void addSaveButtonListener(){
        symptomView.getSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(symptomView.getVerticalLayout().getComponentCount() == 0){
                    return;
                }
                if(!checkDateIntegrity()){
                    return;
                }
                if(symptomView.getDateTimeField().getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    symptomView.getSave().setEnabled(false);
                    return;
                }
                for(SymptomSelectionViewController s: symptomSelectionViewControllers){
                    avoidDuplicateSymptomsSet.add(s.getSelectedSymptom());
                    System.out.println(s.getSelectedSymptom());
                }
                System.out.println(avoidDuplicateSymptomsSet.size());
                System.out.println(symptomView.getVerticalLayout().getComponentCount());

                if(avoidDuplicateSymptomsSet.size() < symptomView.getVerticalLayout().getComponentCount()){
                    Notification.show("Symptome dürfen nur einmal angegeben werden!");
                    symptomView.getSave().setEnabled(false);
                    return;
                }

                HashSet<Symptom> symptoms = new HashSet<>();
                for(SymptomSelectionViewController s: symptomSelectionViewControllers){

                    if(s.getChoice() == null){
                        Notification.show("Es fehlen noch Eingaben für das Symptom: "+ s.getSelectedSymptom(), Notification.Type.HUMANIZED_MESSAGE);
                        return;
                    }
                    if(s.getSelectedSymptom() == null){
                        Notification.show("Es fehlen noch Eingaben.", Notification.Type.HUMANIZED_MESSAGE);
                        return;
                    }
                    symptoms.add(s.getSymptomArt());

                }
                saveDiaryEntry(symptomView.getDateTimeField().getValue(), symptoms);
                symptomView.getNewDiaryEntry().click();


                Notification.show("Eintrag erfolgreich gespeichert");

            }
        });
    }

    public void saveDiaryEntry(LocalDateTime datum, Set<Symptom> symptoms){
        DiaryManager diaryManager = DiaryManager.getInstance();
        //diaryManager.addDiary(new Diary());
        Diary diary = diaryManager.read().get(0);
        long diaryId = diary.getId();


        DiaryEntry diaryEntry = new DiaryEntry(datum, symptoms);  //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        DiaryManager.getInstance().addDiaryEntry(diaryEntry, diaryId);
        DiaryEvaluationViewController.set = diaryManager.readDiaryEntriesByDiary(diaryId); //Evaluation View aktualisieren
    }

    public void addNewDiaryEntryButtonListener(){
        symptomView.getNewDiaryEntry().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(symptomSelectionViewControllers.size() > 1) {
                    Notification.show("Die Einträge werden verworfen");
                    //TODO: Dialog Window with Yes or No
                }

                symptomView.getVerticalLayout().removeAllComponents();
                symptomSelectionViewControllers.clear();
                symptomList.clear();
                avoidDuplicateSymptomsSet.clear();

                symptomList = createSymptomList();

                counter = 0;
               addNewSymptomSelectionView();

            }
        });
    }



    public SymptomView getSymptomView(){
        return symptomView;
    }
    public List<SymptomSelectionViewController> getSymptomSelectionViewControllers(){
        return this.symptomSelectionViewControllers;
    }

    public int getNumberOfSymptoms(){
        return numberOfSymptoms;
    }
}
