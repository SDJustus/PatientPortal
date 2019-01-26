package de.tud.view.homework;

import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import de.tud.controller.HomeworkController;

public class HomeworkSetup extends HomeworkDesigner implements View {

    /**
     * Adds functions the UI elements in the calendar view.
     */

HomeworkController controller;

    /**
     * Adds captions and listener to the project.
     */
    HomeworkSetup() {


        //instantiate controller
    controller = new HomeworkController(this);

    controller.addDateTimeFieldChangeListener();
    controller.addSaveButtonListener();
    controller.addCalenderListenerForCaptionLabel();


    repeatBox.setItems("Einmalig", "Täglich", "Wöchentlich bis Endtermin" );
    repeatBox.setCaption("Wiederholen:");

    combobox.setCaption("Aufgabentyp:");
    dataPicker.setCaption("Fälligkeitsdatum/Letzte Wiederholung:");

    repeatBox.setValue("Einmalig");

        saveButton.setIcon(new ClassResource("/saveicon.png"));


    }





    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public ComboBox<String> getCombobox() {
        return combobox;
    }

    public void setCombobox(ComboBox<String> combobox) {
        this.combobox = combobox;
    }

    public DateField getDataPicker() {
        return dataPicker;
    }

    public void setDataPicker(DateField dataPicker) {
        this.dataPicker = dataPicker;
    }

    public HomeworkCalender getCalendar() {
        return calendar;
    }

    public void setCalendar(HomeworkCalender calendar) {
        this.calendar = calendar;
    }


    public TextField getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(TextField homeworkName) {
        this.homeworkName = homeworkName;
    }

    public TextArea getHomeworkDescription() {
        return homeworkDescription;
    }

    public void setHomeworkDescription(TextArea homeworkDescription) {
        this.homeworkDescription = homeworkDescription;
    }




    public Label getCalenderLabel() {
        return calenderLabel;
    }

    public void setCalenderLabel(Label calenderLabel) {
        this.calenderLabel = calenderLabel;
    }

    public ComboBox getRepeatBox()
    {

        return this.repeatBox;

    }



}



















