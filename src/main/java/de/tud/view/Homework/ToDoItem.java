package de.tud.view.Homework;

import org.vaadin.addon.calendar.item.BasicItem;

public class ToDoItem extends BasicItem {

   public ToDoItem()
    {

        this.setStyleName("v-calendar-event-statetoDo");

    }
    @Override
    public String getStyleName() {
        return "statetoDo";
    }


}
