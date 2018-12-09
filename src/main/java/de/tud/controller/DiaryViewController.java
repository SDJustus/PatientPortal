package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import de.tud.view.DiaryEvaluation.DiaryEvaluationView;
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
    private int counter = 0;

    public DiaryViewController(DiaryView diaryView){
        this.diaryView = diaryView;
        this.symptomList = createSymptomList();

        integrityRestrictionsDateTimeField();
        addNewSymptomSelectionView();
        diaryView.getDateTimeField().setValue(LocalDateTime.now());
    }

    public void addDateTimeFieldChangeListener(){
       diaryView.getDateTimeField().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {


                if(diaryView.getDateTimeField().getValue() == null && checkSymptomSelection() && checkDateIntegrity()){
                    diaryView.getSave().setEnabled(true);
                }else{
                    Notification.show("unzulässiges Datum gewählt!");
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
    public boolean checkDateIntegrity(){
        LocalDateTime rangeEnd = diaryView.getDateTimeField().getRangeEnd();
        LocalDateTime rangeStart = diaryView.getDateTimeField().getRangeStart();
        LocalDateTime date = diaryView.getDateTimeField().getValue();
        if(date.isAfter(rangeEnd) || date.isBefore(rangeStart)){
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
        //TODO: Integritätsbedingungen in DiaryViewController
        diaryView.getDateTimeField().setRangeStart(LocalDateTime.now().minusWeeks(3));
        diaryView.getDateTimeField().setRangeEnd(LocalDateTime.now().plusMinutes(1));
    }


    public void addNewSymptomSelectionView(){
        SymptomSelectionView symptomSelectionView = new SymptomSelectionView(this);
        diaryView.getVerticalLayout().addComponents(symptomSelectionView.getViewComponent());

        System.out.println(diaryView.getVerticalLayout().getComponentCount());


        symptomSelectionViewControllers.add(symptomSelectionView.getSymptomSelectionViewController());
        counter++;
        diaryView.getEdit().setEnabled(true);

    }

    public void setSaveButtonEnabled(boolean value) {
        diaryView.getSave().setEnabled(value);
    }

    //TODO Check Save Button Methode
    public void addSaveButtonListener(){
        diaryView.getSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(diaryView.getVerticalLayout().getComponentCount() == 0){
                    return;
                }
                if(!checkDateIntegrity()){
                    return;
                }
                if(diaryView.getDateTimeField().getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    diaryView.getSave().setEnabled(false);
                    return;
                }
                for(SymptomSelectionViewController s: symptomSelectionViewControllers){
                    avoidDuplicateSymptomsSet.add(s.getSelectedSymptom());
                    System.out.println(s.getSelectedSymptom());
                }
                System.out.println(avoidDuplicateSymptomsSet.size());
                System.out.println(diaryView.getVerticalLayout().getComponentCount());

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
                diaryView.getNewDiaryEntry().click();


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
        diaryView.getNewDiaryEntry().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(symptomSelectionViewControllers.size() > 1) {
                    Notification.show("Die Einträge werden verworfen");
                    //TODO: Dialog Window with Yes or No
                }
                diaryView.getVerticalLayout().removeAllComponents();
                symptomSelectionViewControllers.clear();
                symptomList.clear();
                avoidDuplicateSymptomsSet.clear();

                symptomList = createSymptomList();

                counter = 0;
               addNewSymptomSelectionView();
               diaryView.getOkay().setEnabled(false);
               diaryView.getEdit().setEnabled(true);
            }
        });
    }
    public void addNewEditButtonListener(){
        diaryView.getEdit().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                for(SymptomSelectionViewController s : symptomSelectionViewControllers){
                    if(s.checkComboBox() == true){
                        s.getSymptomSelectionView().getDelete().setVisible(true);
                        diaryView.getOkay().setEnabled(true);
                        diaryView.getEdit().setEnabled(false);
                    }
                }
            }
        });
    }
    public void addOkayButtonListener(){
        diaryView.getOkay().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                for(SymptomSelectionViewController s: symptomSelectionViewControllers){
                    s.getSymptomSelectionView().getDelete().setVisible(false);
                }
                diaryView.getOkay().setEnabled(false);
                diaryView.getEdit().setEnabled(true);
            }
        });
    }

    public DiaryView getDiaryView(){
        return  diaryView;
    }
    public List<SymptomSelectionViewController> getSymptomSelectionViewControllers(){
        return this.symptomSelectionViewControllers;
    }


}
