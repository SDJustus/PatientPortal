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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }













}



















