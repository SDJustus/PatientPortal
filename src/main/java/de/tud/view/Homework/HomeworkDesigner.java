package de.tud.view.Homework;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import org.vaadin.addon.calendar.Calendar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Panel;
import com.vaadin.ui.DateField;


/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class HomeworkDesigner extends Panel {
    protected Button saveButton;
    protected ComboBox<String> combobox;
    protected DateField dataPicker;
    protected TextField homeworkName;
    protected TextField homeworkDescription;
    protected TextField homeworkDescriptionLong;
    protected Calendar calendar;


    public HomeworkDesigner() {
        Design.read(this);
    }


}
