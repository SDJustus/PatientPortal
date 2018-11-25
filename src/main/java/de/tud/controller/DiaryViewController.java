package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalDataSet;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import de.tud.view.DiaryView;
import de.tud.view.SymptomSelectionView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiaryViewController {


    private DiaryView diaryView;
    private HashSet<String> avoidDuplicateSymptomsSet = new HashSet<>();   //Eingabe von doppelten Symptomen vermeiden
    private List<SymptomSelectionViewController> symptomSelectionViewControllers = new ArrayList<>();
    private List<String> symptomList;


    public DiaryViewController(DiaryView diaryView){
        this.diaryView = diaryView;
        this.symptomList = createSymptomList();

        integrityRestrictionsDateTimeField();
        SymptomSelectionView symptomSelectionView = new SymptomSelectionView(this);
        diaryView.getVerticalLayout().addComponent(symptomSelectionView.getSymptomSelectionView());

    }

    public void addDateTimeFieldChangeListener(){
       diaryView.getDateTimeField().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
                if(diaryView.getDateTimeField().getValue() == null && checkSymptomSelection()){
                    diaryView.getSave().setEnabled(true);
                }
            }
        });

    }
    public List<String> getSymptomList(){
        return  symptomList;
    }


    private List<String> createSymptomList(){
        List<String> symptomList = new ArrayList<>();
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
        //TODO: Integritätsbedingungen in DiaryViewController
        diaryView.getDateTimeField().setRangeStart(LocalDateTime.now().minusWeeks(3));
        diaryView.getDateTimeField().setRangeEnd(LocalDateTime.now().plusMinutes(1));
    }
    public void addNewSymptomSelectionView(SymptomSelectionView symptomSelectionView){
        diaryView.getVerticalLayout().addComponents(symptomSelectionView.getSymptomSelectionView());
        symptomSelectionViewControllers.add(symptomSelectionView.getSymptomSelectionViewController());
    }

    public void setSaveButtonEnabled(boolean value) {
        diaryView.getSave().setEnabled(value);
    }

    //TODO Check Save Button Methode
    public void addSaveButtonListener(){
        diaryView.getSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(diaryView.getDateTimeField().getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    diaryView.getSave().setEnabled(false);
                    return;
                }

                for(SymptomSelectionViewController s: symptomSelectionViewControllers){
                    avoidDuplicateSymptomsSet.add(s.getSelectedSymptom());
                }

                if(avoidDuplicateSymptomsSet.size() < diaryView.getVerticalLayout().getComponentCount()){
                    Notification.show("Symptome dürfen nur einmal angegeben werden!");
                    diaryView.getSave().setEnabled(false);
                    return;
                }

                HashSet<Symptom> symptoms = new HashSet<>();
                for(SymptomSelectionViewController s: symptomSelectionViewControllers){
                    System.out.println(s.getSelectedSymptom()+ " "+s.getChoice());
                    symptoms.add(s.getSymptomArt());
                }
                saveDiaryEntry(diaryView.getDateTimeField().getValue(), symptoms);
                Notification.show("Eintrag erfolgreich gespeichert");

            }
        });
    }

    public void saveDiaryEntry(LocalDateTime datum, Set<Symptom> symptoms){
        DiaryManager diaryManager = new DiaryManager();
        //diaryManager.addDiary(new Diary());
        Diary diary = diaryManager.read().get(0);
        long diaryId = diary.getId();

        DiaryEntry diaryEntry = new DiaryEntry(datum, symptoms, new VitalDataSet());  //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        DiaryManager.getInstance().addDiaryEntry(diaryEntry, diaryId);

    }

    public void addNewDiaryEntryButtonListener(){
        SymptomSelectionView symptomSelectionView = new SymptomSelectionView(this);
        diaryView.getNewDiaryEntry().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(symptomSelectionViewControllers.size() > 1) {
                    Notification.show("Die Einträge werden verworfen");
                    //TODO: Dialog Window with Yes or No

                }
                diaryView.getVerticalLayout().removeAllComponents();
                symptomSelectionViewControllers.clear();
                diaryView.getSave().setEnabled(false);
                symptomList.clear();
                symptomList = createSymptomList();
                diaryView.getVerticalLayout().addComponent(symptomSelectionView.getSymptomSelectionView());
            }
        });
    }
    public DiaryView getDiaryView(){
        return  diaryView;
    }



}
