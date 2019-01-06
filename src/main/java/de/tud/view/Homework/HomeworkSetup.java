package de.tud.view.Homework;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import de.tud.controller.HomeworkController;
import org.vaadin.addon.calendar.Calendar;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

public class HomeworkSetup extends HomeworkDesigner implements View {



HomeworkController controller;


    HomeworkSetup() {


        //instantiate controller
    controller = new HomeworkController(this);

    controller.addDateTimeFieldChangeListener();
    controller.addSaveButtonListener();
    controller.addCalenderListenerForCaptionLabel();




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







}



















