package de.tud.controller;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.*;
import com.vaadin.ui.*;

import java.time.LocalDateTime;
import java.util.*;
import de.tud.model.Diary;
import de.tud.model.symptom.Depression;
import de.tud.model.symptom.Symptom;
import de.tud.view.*;



public class TagebuchImplementierung extends Tagebuch {


    private List<Diary> tagebuch = new ArrayList<Diary>();
    private String choice = "";

    @Override
    public void setStyleName(String style, boolean add) {

    }

    public TagebuchImplementierung(){

        //Tagebucheinträge laden
        if(!TagebucheintragManager.read().isEmpty()) {
            tagebuch = loadDiaryEntries();
            table.setItems(tagebuch);
        }
        datePicker.addValueChangeListener(event -> checkSaveButton());

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
        //Beschriftung der Tabellenzeilen
        table.addColumn(DataModelDiary::getDate).setCaption("Datum");
        table.addColumn(DataModelDiary::getSymptom).setCaption("Ausprägung der Symptome");

        //Definition von CSS-Styleklasse für Zoom Effekt der Smileys
        goodSmiley.setId("smileybild");
        middleSmiley.setId("smileybild");
        badSmiley.setId("smileybild");

        //saveButton standardmäßig deaktiviert, ersten wenn Eingabe getätigt wurde, dann Aktivierung
        saveButton.setEnabled(false);




        //Event Handler für Klick auf die Smileys
        goodSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                middleLabel.setValue("");
                badLabel.setValue("");
                goodlabel.setValue("keine");
                choice = goodlabel.getValue();
                checkSaveButton();
            }
        });
        middleSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                goodlabel.setValue("");
                badLabel.setValue("");
                middleLabel.setValue("mäßig");
                choice = middleLabel.getValue();
                checkSaveButton();

            }
        });
        badSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                goodlabel.setValue("");
                middleLabel.setValue("");
                badLabel.setValue("stark");
                choice = badLabel.getValue();
                checkSaveButton();

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
                        if(mood.equals("stark")) {
                            tagebuch.add(new DataModelDiary(datum, new Symptom(Symptom.Strength.SEVERE) {
                            }));
                            saveDiaryEntry(new Depression(Symptom.Strength.SEVERE));


                        }if(mood.equals("mäßig")) {

                        tagebuch.add(new DataModelDiary(datum, new Symptom(Symptom.Strength.MIDDLE) {
                        }));
                        saveDiaryEntry(new Depression(Symptom.Strength.MIDDLE));
                        }

                        if(mood.equals("keine")) {
                        tagebuch.add(new DataModelDiary(datum, new Symptom(Symptom.Strength.WEAK) {
                        }));
                        saveDiaryEntry(new Depression(Symptom.Strength.WEAK));
                        }

                        tagebuch = loadDiaryEntries();
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
    public void saveDiaryEntry(Symptom symptom){
            ArrayList<Symptom> entry = new ArrayList<>();
            entry.add(symptom);
            TagebucheintragManager.create(new Tagebucheintrag(entry, datePicker.getValue()));
    }

    public List<DataModelDiary> loadDiaryEntries(){
            List<Tagebucheintrag> tagebucheintragList = TagebucheintragManager.read();
            List<DataModelDiary> dateEntriesForUI = new ArrayList<>();


            for(Tagebucheintrag t:tagebucheintragList){
               dateEntriesForUI.add(new DataModelDiary(t.getDate(), t.getSymptoms().get(0)));
            }
        return dateEntriesForUI;
    }




}
