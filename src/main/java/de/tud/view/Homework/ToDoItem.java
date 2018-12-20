package de.tud.view.Homework;

import org.vaadin.addon.calendar.item.BasicItem;

import java.time.ZonedDateTime;

public class ToDoItem extends BasicItem {

   public ToDoItem()
    {

        this.setStyleName("v-calendar-event-statetoDo");

    }
    @Override
    public String getStyleName() {
        return "statetoDo";
    }


    @Override
    public boolean isMoveable() {
        return false;
    }
    @Override
    public boolean isResizeable()
    {
        return false;

    }

    @Override
    public boolean isAllDay() {
        return true;
    }



}
