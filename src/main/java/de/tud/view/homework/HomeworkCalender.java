package de.tud.view.homework;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import de.steinwedel.messagebox.ButtonOption;
import de.steinwedel.messagebox.MessageBox;
import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.item.BasicItem;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import java.util.List;

public class HomeworkCalender extends Calendar {


    HomeworkDesigner designView;

    public HomeworkCalender()
    {
        addCalendarEventListeners();


    }

    private void onCalendarClick(CalendarComponentEvents.ItemClickEvent event) {
        Notification notification = new Notification("");
        BasicItem item = (BasicItem) event.getCalendarItem();
        List<Homework> homeworkList = HomeworkManager.getInstance().read();

        //Setup Dialog


        //MEssage Box


        String name = "";
        String typ ="";
        String status ="Offen";
        String description =  "";

        for(Homework work:homeworkList)
        {

            if(item instanceof ToDoItem == true)
            {

                ToDoItem toDO = (ToDoItem) item;

                if(toDO.getId() == work.getId())  {

                    name = work.getName();
                    description = "Beschreibung: "+work.getDescription();
                    typ = "Typ: " + work.getType().toString();

                }

            }
            if(item instanceof FinishedItem == true)
            {
                typ = "Typ: " + work.getType().toString();
                FinishedItem finished = (FinishedItem) item;

                if(finished.getId() == work.getId())  {

                    name = work.getName();
                    description = "Beschreibung: "+work.getDescription();
                    status= "Status: Erledigt";


                }

            }

        }




        MessageBox
                .createInfo()
                .withCaption(name)
                .withMessage(status+"\n"+typ+"\n"+description)
                .withNoButton(ButtonOption.caption("Weiter"), ButtonOption.icon(VaadinIcons.ARROW_LEFT))
                .withYesButton( () -> { System.out.println("Aufgabe erledigt");

                    for(Homework work:homeworkList)
                    {
                        if(item instanceof ToDoItem == true)
                        {

                            ToDoItem toDO = (ToDoItem) item;

                            if(toDO.getId() == work.getId())  {


                                HomeworkManager.getInstance().setHomeworkStatus(work.getId(), true);

                            }

                        }
                         if(item instanceof FinishedItem == true)
                        {

                            FinishedItem finished = (FinishedItem) item;

                            if(finished.getId() == work.getId())  {


                            HomeworkManager.getInstance().setHomeworkStatus(work.getId(), true);

                        }

                        }


                    }
                    this.getUI().access(new Runnable() {
                        @Override
                        public void run() {

                            UI.getCurrent().getNavigator().navigateTo("Hausaufgaben");


                        }
                    });





                }, ButtonOption.caption("Erledigt"))
                .withCancelButton(()->{

                    for(Homework work:homeworkList)
                    {


                        if(item instanceof ToDoItem == true)
                        {

                            ToDoItem toDO = (ToDoItem) item;

                            if(toDO.getId() == work.getId())  {


                                HomeworkManager.getInstance().delete(work.getId());

                            }

                        }
                        if(item instanceof FinishedItem == true)
                        {

                            FinishedItem finished = (FinishedItem) item;

                            if(finished.getId() == work.getId())  {


                                HomeworkManager.getInstance().delete(work.getId());

                            }

                        }


                    }
                    this.getUI().access(new Runnable() {
                        @Override
                        public void run() {

                     UI.getCurrent().getNavigator().navigateTo("Hausaufgaben");


                        }
                    });



                }, ButtonOption.caption("Eintrag löschen"))
                .open();



    }

    private void addCalendarEventListeners() {
        this.setHandler(new BasicDateClickHandler(true));
        this.setHandler(this::onCalendarClick);

    }




}
