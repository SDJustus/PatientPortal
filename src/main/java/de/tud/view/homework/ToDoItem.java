package de.tud.view.homework;

import org.vaadin.addon.calendar.item.BasicItem;

public class ToDoItem extends BasicItem {

    private long id;

   public ToDoItem(long id)
    {
        this.id = id;
        this.setStyleName("toDoEvent");

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getStyleName() {
        return "toDoEvent";
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
