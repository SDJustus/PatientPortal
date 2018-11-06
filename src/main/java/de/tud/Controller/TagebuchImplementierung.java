package de.tud.Controller;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import java.util.*;
import de.tud.Model.DataModelDiary;
import de.tud.Model.symptom.Depression;
import de.tud.Model.symptom.Symptom;
import de.tud.View.*;



public class TagebuchImplementierung extends Tagebuch {


    private List<DataModelDiary> tagebuch = new ArrayList<DataModelDiary>();
    private String choice;

    @Override
    public void setStyleName(String style, boolean add) {

    }

    public TagebuchImplementierung(){
        //Smiley Bilder laden
        goodSmiley.setSource(new ClassResource("/gut.png"));
        middleSmiley.setSource(new ClassResource("/mittel.png"));
        badSmiley.setSource(new ClassResource("/schlecht.png"));

        //Einstellungen für Größe der Tabelle
        table.setHeight(""+0.7*Page.getCurrent().getBrowserWindowHeight());
        table.setWidth(""+0.7*Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
           table.setHeight(""+0.7*e.getHeight());
           table.setWidth(""+0.7*e.getWidth());

        });
        //Beschriftung der Tabellenzeilen
        table.addColumn(DataModelDiary::getDate).setCaption("Datum");
        table.addColumn(DataModelDiary::getSymptom).setCaption("Ausprägung der Symptome");

        
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
                        String datum = datePicker.getValue().toString();
                        String mood = choice;

                //TODO: change mood Type to enum and implement Symptomfactory instead of concret Symptom
                switch (mood) {
                    case "stark":
                        tagebuch.add(new DataModelDiary(datum, new Depression(Symptom.Strength.SEVERE)));
                        break;
                    case "mäßig":
                        tagebuch.add(new DataModelDiary(datum, new Depression(Symptom.Strength.MIDDLE)));
                        break;
                    case "keine":
                        tagebuch.add(new DataModelDiary(datum, new Depression(Symptom.Strength.WEAK)));
                        break;
                    default: throw new IllegalArgumentException("received wrong mood");
                }
                        table.setItems(tagebuch);
                    Notification.show("Eintrag erfolgreich gespeichert");

                }


            }
        });





    }




}
