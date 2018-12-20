package de.tud.view.Homework;

import org.vaadin.addon.calendar.item.BasicItem;

import java.time.ZonedDateTime;

public class FinishedItem extends BasicItem {

   public FinishedItem()
    {

        this.setStyleName("v-calendar-event-statefinished");


    }

    @Override
    public String getStyleName() {
        return "statefinished";
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


