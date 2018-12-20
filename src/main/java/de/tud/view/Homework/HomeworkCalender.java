package de.tud.view.Homework;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import de.steinwedel.messagebox.MessageBox;
import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.item.BasicItem;
import org.vaadin.addon.calendar.item.CalendarItemProvider;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomeworkCalender extends Calendar {


    public HomeworkCalender()
    {
        addCalendarEventListeners();

    }

    private void onCalendarClick(CalendarComponentEvents.ItemClickEvent event) {
        Notification notification = new Notification("");
        BasicItem item = (BasicItem) event.getCalendarItem();
        HomeworkManager manager = new HomeworkManager();
        List<Homework> homeworkList = manager.read();

        //Setup Dialog


        //MEssage Box


        String name = "";
        String status ="Status: zu erledigen";
        String description =  "";

        for(Homework work:homeworkList)
        {


            if(work.getName().equals(item.getCaption()) )
            {
                name = work.getName();
                 description = "Beschreibung: "+work.getDescription();

                if(work.isStatus() == true)
                {

                status= "Status: erledigt";

                }






            }



        }




        MessageBox
                .createInfo()
                .withCaption(name)
                .withMessage(status+"\n"+description)
                .withOkButton().open();





    }

    private void addCalendarEventListeners() {
        this.setHandler(new BasicDateClickHandler(true));
        this.setHandler(this::onCalendarClick);

    }




}
