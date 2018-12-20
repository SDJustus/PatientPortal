package de.tud.view.Homework;

import org.vaadin.addon.calendar.item.BasicItem;

public class FinishedItem extends BasicItem {

   public FinishedItem()
    {

        this.setStyleName("v-calendar-event-statefinished");


    }

    @Override
    public String getStyleName() {
        return "statefinished";
    }


}
