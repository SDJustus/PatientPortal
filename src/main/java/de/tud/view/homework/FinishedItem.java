package de.tud.view.homework;

import org.vaadin.addon.calendar.item.BasicItem;


public class FinishedItem extends BasicItem {

    /**
     * Identification number for calendar elements.
     * The same id is used in the database.
     */

private long id;

    /**
     * Sets and style name.
     */

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


