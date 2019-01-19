package de.tud.view.Homework;

import org.vaadin.addon.calendar.item.BasicItem;

import java.time.ZonedDateTime;



public class FinishedItem extends BasicItem {

private long id;

   public FinishedItem(long id)
    {

        this.id= id;

        this.setStyleName("finishedEvent");


    }

    @Override
    public String getStyleName() {
        return "finishedEvent";
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}


