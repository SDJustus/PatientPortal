package de.tud.Controller;

import com.vaadin.event.MouseEvents;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.server.*;
import com.vaadin.shared.Registration;
import com.vaadin.ui.*;

import java.time.LocalDateTime;
import java.util.*;
import de.tud.Model.DataModelDiary;
import de.tud.Model.Depression;
import de.tud.Model.Symptom;
import de.tud.View.*;



public class TagebuchImplementierung extends Tagebuch {


    private List<DataModelDiary> tagebuch = new ArrayList<DataModelDiary>();
    private String choice;
    private String selectedSymptom;

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

        //adding symptoms to the combobox

        List<String> symptomList = new ArrayList<>();
        symptomList.add("Depression");
        symptomList.add("Schmerzen");
        symptomList.add("Spastik");
        symptomList.add("Kognitive Störung");
        symptomList.add("Spastik am rechten Arm");
        symptomList.add("Spastik am linken Arm");
        symptomList.add("Spastik am linken Bein");
        //.... to be continued -> eventuell später als Symptome speichern und dann als toString übergeben?

        //adding symptoms
        symptomBox.setItems(symptomList);

        //listener for selectedSymptom

        symptomBox.addSelectionListener(new SingleSelectionListener() {
            @Override
            public void selectionChange(SingleSelectionEvent singleSelectionEvent) {
                selectedSymptom = symptomBox.getSelectedItem().toString();
            }
        });




        //Event Handler für Klick auf die Smileys
        goodSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                middleLabel.setValue("");
                badLabel.setValue("");
                goodlabel.setValue("schwach");
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
                        if(mood.equals("stark"))
                    {
                        tagebuch.add(new DataModelDiary(datum, new Symptom(Symptom.Strength.SEVERE) {
                        }));
                    }
                    if(mood.equals("mäßig"))
                    {
                        tagebuch.add(new DataModelDiary(datum, new Symptom(Symptom.Strength.MIDDLE) {
                        }));
                    }
                    if(mood.equals("keine"))
                    {
                        tagebuch.add(new DataModelDiary(datum, new Symptom(Symptom.Strength.WEAK) {
                        }));
                    }



                        table.setItems(tagebuch);
                    Notification.show("Eintrag erfolgreich gespeichert");
                    symptomBox.setSelectedItem(null);
                }


            }
        });





    }







}
