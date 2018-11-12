package de.tud.controller;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.*;
import com.vaadin.ui.*;

import java.time.LocalDateTime;
import java.util.*;
import de.tud.model.*;
import de.tud.model.DiaryEntryTableViewAdapter;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Depression;
import de.tud.model.symptom.Symptom;
import de.tud.view.*;



public class TagebuchImplementierung extends Tagebuch {


    private List<DiaryEntryTableViewAdapter> tagebuch = new ArrayList<>();
    private String choice;
    DiaryManager diaryManager;

    @Override
    public void setStyleName(String style, boolean add) {

    }

    public TagebuchImplementierung(){
        diaryManager = new DiaryManager();

        if(!diaryManager.readDiaryEntry().isEmpty()) {
            tagebuch = loadDiaryEntries();
            table.setItems(tagebuch);
        }
        //datePicker.addValueChangeListener(event -> checkSaveButton());


        //Smiley Bilder laden
        goodSmiley.setSource(new ClassResource("/gut.png"));
        middleSmiley.setSource(new ClassResource("/mittel.png"));
        badSmiley.setSource(new ClassResource("/schlecht.png"));

        //Einstellungen für Größe der Tabelle
        table.setHeight(""+0.3*Page.getCurrent().getBrowserWindowHeight());
        table.setWidth(""+0.7*Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
           table.setHeight(""+0.3*e.getHeight());
           table.setWidth(""+0.7*e.getWidth());

        });
        //TODO: implement correct statements

        //Beschriftung der Tabellenzeilen
        table.addColumn(DiaryEntryTableViewAdapter::getDate).setCaption("Datum");
        table.addColumn(DiaryEntryTableViewAdapter::getSymptom).setCaption("Ausprägung der Symptome");


        //Definition von CSS-Styleklasse für Zoom Effekt der Smileys
        goodSmiley.setId("smileybild");
        middleSmiley.setId("smileybild");
        badSmiley.setId("smileybild");



        //Event Handler für Klick auf die Smileys
        goodSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                middleLabel.setValue("");
                badLabel.setValue("");
                goodlabel.setValue("keine");
                choice = goodlabel.getValue();
            }
        });
        middleSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                goodlabel.setValue("");
                badLabel.setValue("");
                middleLabel.setValue("mäßig");
                choice = middleLabel.getValue();

            }
        });
        badSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                goodlabel.setValue("");
                middleLabel.setValue("");
                badLabel.setValue("stark");
                choice = badLabel.getValue();

            }
        });

        //Event Handler für Klick auf den Speicher Button
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //falls kein Datum ausgewählt wurde, dann Fehlermeldung
                if(datePicker.getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    return;
                }
                /*falls Datum ausgewählt wurde, und das TextLabel unterhalb der Smileys
                einen Wert hat, dann kann der Eintrag in der Tabelle gespeichert bzw
                 angezeigt werden */

                if(datePicker.getValue() != null && !choice.equals("")){
                        LocalDateTime datum = datePicker.getValue();
                        String mood = choice;
                //TODO: implement correct statements

                //TODO: change mood Type to enum and implement Symptomfactory instead of concret Symptom
                    Set<Symptom> symptoms = new HashSet<>();

                switch (mood) {
                    case "stark":
                        symptoms.add(new Depression(Symptom.Strength.SEVERE));
                        saveDiaryEntry(datum, symptoms);
                        //tagebuch.add(new DiaryEntryTableViewAdapter(datum, symptoms));
                        break;
                    case "mäßig":
                        //tagebuch.add(new Diary(datum, new Depression(Symptom.Strength.MIDDLE)));
                        symptoms.add(new Depression(Symptom.Strength.MIDDLE));
                        saveDiaryEntry(datum, symptoms);
                        break;
                    case "keine":
                        //tagebuch.add(new Diary(datum, new Depression(Symptom.Strength.WEAK)));
                        symptoms.add(new Depression(Symptom.Strength.WEAK));
                        break;
                    default: throw new IllegalArgumentException("received wrong mood");

                     }

                     loadDiaryEntries();
                    table.setItems(tagebuch);
                    Notification.show("Eintrag erfolgreich gespeichert");
                }
            }
        });
    }

    public void checkSaveButton(){
        if(datePicker.getValue() != null && !choice.equals("")){
            saveButton.setEnabled(true);
        }
    }

    public void saveDiaryEntry(LocalDateTime datum, Set<Symptom> symptoms){
        DiaryEntry diaryEntry = new DiaryEntry(datum , symptoms);
        diaryManager.addDiaryEntry(diaryEntry,diaryEntry.getId());
    }

    public List<DiaryEntryTableViewAdapter> loadDiaryEntries(){
        List<DiaryEntry> tagebucheintragList = diaryManager.readDiaryEntry();
        List<DiaryEntryTableViewAdapter> diaryEntriesForUI = new ArrayList<>();

            System.out.println(tagebucheintragList.size());
        System.out.println(tagebucheintragList.get(0).getDate());


            for(DiaryEntry diaryEntry : tagebucheintragList){
                for(Symptom symptom : diaryEntry.getSymptom()){
                    System.out.println(symptom);
                    diaryEntriesForUI.add(new DiaryEntryTableViewAdapter(diaryEntry.getDate().toString(), symptom));
                }

            }

        return diaryEntriesForUI;
    }
}
