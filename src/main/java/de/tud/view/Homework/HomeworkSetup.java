package de.tud.view.Homework;

import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import de.tud.view.Homework.Calendar.HomeworkCalendar;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

public class HomeworkSetup extends HomeworkDesigner implements View {


    HomeworkCalendar home = new HomeworkCalendar();

    VerticalLayout calenderlayout;
    ResponsiveRow row;

    HomeworkSetup() {

        row = this.addRow();
        setScrollable(true);




        home.setSizeFull();
        home.setResponsive(true);


        calenderlayout = new VerticalLayout();
        calenderlayout.addComponent(home);
        calenderlayout.setSizeFull();




        row.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(calenderlayout);


    }



}



















