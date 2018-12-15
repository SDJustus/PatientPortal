package de.tud.controller;

import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.ContentMode;
import de.tud.model.Diary;
import de.tud.model.Homework;
import de.tud.model.manager.DiaryManager;
import de.tud.model.manager.HomeworkManager;
import de.tud.model.welfare.Welfare;
import de.tud.view.Homework.HomeworkDesigner;
import de.tud.view.Homework.HomeworkSetup;
import de.tud.view.Welfare.WelfareUISetup;
import org.vaadin.addon.calendar.item.BasicItem;
import org.vaadin.addon.calendar.item.BasicItemProvider;
import org.vaadin.addon.calendar.item.CalendarItemProvider;

import javax.xml.crypto.Data;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class HomeworkController {


    HomeworkSetup designerView;
    private long diaryId;
    DiaryManager diaryManager;
    Diary diary;




    public  HomeworkController(HomeworkSetup designerView)
    {
        this.designerView = designerView;
        diaryManager = DiaryManager.getInstance();
        diary = diaryManager.read().get(0);
        diaryId = diary.getId();
        this.designerView.getSaveButton().setEnabled(false);
        setUpCalendar();

    }



    void setUpCalendar()
    {

        designerView.getCalendar().setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        designerView.getCalendar().setHeight(100.0f, Sizeable.Unit.PERCENTAGE);
        designerView.getCalendar().setResponsive(true);

        designerView.getCalendar().setItemCaptionAsHtml(true);
        designerView.getCalendar().setContentMode(ContentMode.HTML);

        designerView.getCalendar().setLocale(Locale.GERMANY);
//        calendar.setZoneId(ZoneId.of("America/Chicago"));
//        calendar.setWeeklyCaptionProvider(date ->  "<br>" + DateTimeFormatter.ofPattern("dd.MM.YYYY", getLocale()).format(date));
//        calendar.setWeeklyCaptionProvider(date -> DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(getLocale()).format(date));

       // designerView.getCalendar().withVisibleDays(1, 7);
          designerView.getCalendar().withMonth(ZonedDateTime.now().getMonth());

        //designerView.getCalendar().setStartDate(ZonedDateTime.of(2017, 9, 10, 0,0,0, 0, designerView.getCalendar().getZoneId()));
        // designerView.getCalendar().setEndDate(ZonedDateTime.of(2050, 9, 16, 0,0,0, 0, designerView.getCalendar().getZoneId()));
       // setupBlockedTimeSlots();

        loadCalendarEntries();

    }


    private void setupBlockedTimeSlots() {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(java.util.Calendar.MINUTE);
        cal.clear(java.util.Calendar.SECOND);
        cal.clear(java.util.Calendar.MILLISECOND);

        GregorianCalendar bcal = new GregorianCalendar(designerView.getCalendar().getLocale());
        bcal.clear();

        long start = bcal.getTimeInMillis();

        bcal.add(java.util.Calendar.HOUR, 7);
        bcal.add(java.util.Calendar.MINUTE, 30);
        long end = bcal.getTimeInMillis();

        designerView.getCalendar().addTimeBlock(start, end, "my-blocky-style");

        cal.add(java.util.Calendar.DAY_OF_WEEK, 1);

        bcal.clear();
        bcal.add(java.util.Calendar.HOUR, 14);
        bcal.add(java.util.Calendar.MINUTE, 30);
        start = bcal.getTimeInMillis();

        bcal.add(java.util.Calendar.MINUTE, 60);
        end = bcal.getTimeInMillis();

        designerView.getCalendar().addTimeBlock(start, end);

    }


    void loadCalendarEntries()
    {
        HomeworkManager manager = new HomeworkManager();
        if(manager.read() == null)
        {
            return;

        }

        List<Homework> homeworkList = manager.read();

       CalendarItemProvider<BasicItem> provider;

        BasicItemProvider<BasicItem> basicProvider = new BasicItemProvider<>();





        for(Homework home:homeworkList)
        {

            if(home == null)
            {
                break;

            }


            BasicItem basic = new BasicItem();
            basic.setStart(home.getDate());
            basic.setEnd(home.getDate());

            basic.setDescription(home.getShortDescription());
            basic.setCaption(home.getName());
            basicProvider.addItem(basic);


        }

           designerView.getCalendar().setDataProvider(basicProvider);


    }

















}
