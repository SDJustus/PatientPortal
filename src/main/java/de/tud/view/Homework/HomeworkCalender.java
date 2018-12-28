package de.tud.view.Homework;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.item.BasicItem;
import org.vaadin.addon.calendar.item.CalendarItemProvider;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

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


        for(Homework work:homeworkList)
        {


            if(work.getName().equals(item.getCaption()) )
            {
                System.out.println("Sind drin");
                notification = new Notification(work.getName()+": ", "Beschreibung: "+work.getDescription(), Notification.Type.HUMANIZED_MESSAGE);

            }


        }




        System.out.println(item.getCaption());

        notification.setDelayMsec(100000);
        notification.show(Page.getCurrent());





    }

    private void addCalendarEventListeners() {
        this.setHandler(new BasicDateClickHandler(true));
        this.setHandler(this::onCalendarClick);

    }




}
