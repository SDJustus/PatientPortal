package de.tud.view.Homework.Calendar;
import com.vaadin.icons.VaadinIcons;
import de.tud.model.Homework;
import org.vaadin.addon.calendar.item.BasicItem;

import java.time.ZonedDateTime;

/**
 * Meeting Pojo
 */

public class HomeworkItem extends BasicItem {


    Homework work;

    public HomeworkItem(Homework work) {

        this.work = work;


    }




    public Homework getHomework() {
        return work;
    }



    @Override
    public int hashCode() {
        return getHomework().hashCode();
    }




    @Override
    public String getDateCaptionFormat() {
        //return CalendarItem.RANGE_TIME;
        return VaadinIcons.CLOCK.getHtml()+" %s<br>" +
                VaadinIcons.ARROW_CIRCLE_RIGHT_O.getHtml()+" %s";
    }

}