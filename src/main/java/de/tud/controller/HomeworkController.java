package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
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
import java.time.*;
import java.util.*;

public class HomeworkController {


    HomeworkSetup designerView;
    private long diaryId;


    BasicItemProvider<BasicItem> basicProvider;



    public  HomeworkController(HomeworkSetup designerView)
    {
        this.designerView = designerView;
        this.designerView.getSaveButton().setEnabled(false);
        setUpCalendar();
        setupComobobox();
        setUpDataPicker();
        addTextBoxRestrictions();

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

        basicProvider = new BasicItemProvider<>();





        for(Homework home:homeworkList)
        {

            if(home == null)
            {
                break;

            }


            BasicItem basic = new BasicItem();
            basic.setStart(home.getDate());
            basic.setEnd(home.getDate());

            basic.setDescription(home.getType().toString()+": "+home.getShortDescription());
            basic.setCaption(home.getName());
            basicProvider.addItem(basic);


        }

           designerView.getCalendar().setDataProvider(basicProvider);



    }


    public void addDateTimeFieldChangeListener(){
        designerView.getDataPicker().addValueChangeListener(new HasValue.ValueChangeListener<LocalDate>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {

                if(designerView.getDataPicker().getValue() != null ){
                    designerView.getSaveButton().setEnabled(true);
                }


            }
        });



        }




    public void addSaveButtonListener() {
        designerView.getSaveButton().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {



                if(designerView.getHomeworkDescription().isEmpty() == true)
                {
                    Notification.show("Bitte füllen Sie alle Felder aus");
                    return;
                }
                if(designerView.getHomeworkDescriptionLong().isEmpty() == true)
                {
                    Notification.show("Bitte füllen Sie alle Felder aus");
                    return;
                }
                if(designerView.getHomeworkName().isEmpty() == true)
                {
                    Notification.show("Bitte füllen Sie alle Felder aus");
                    return;
                }



                Homework h;
                LocalDate local= designerView.getDataPicker().getValue();
                ZonedDateTime zdt = local.atStartOfDay(ZoneOffset.UTC);
                HomeworkManager manager = new HomeworkManager();

                List<Homework> homeworkList = manager.read();

                    if(isDateOccupied(zdt) == true)
                    {
                        Notification.show("Datum und Uhrzeit bereits belegt");
                        designerView.getDataPicker().setValue(null);
                        designerView.getSaveButton().setEnabled(false);
                        return;
                    }



                if(designerView.getCombobox().getValue() == "Fragebogen")
                {

                    h = new Homework(Homework.Type.QUESTIONNAIRE, designerView.getHomeworkName().getValue(),
                            designerView.getHomeworkDescriptionLong().getValue(),  designerView.getHomeworkDescription().getValue(),
                            zdt);
                    manager.create(h);
                }
                if(designerView.getCombobox().getValue() == "Übung")
                {

                     h = new Homework(Homework.Type.EXERCISE, designerView.getHomeworkName().getValue(),
                            designerView.getHomeworkDescriptionLong().getValue(),  designerView.getHomeworkDescription().getValue(),
                            zdt);
                    manager.create(h);

                    System.out.println(h.getName());
                    System.out.println(h.getId().toString());
                }
                if(designerView.getCombobox().getValue() == "Dokument")
                {
                    h = new Homework(Homework.Type.DOKUMENT, designerView.getHomeworkName().getValue(),
                            designerView.getHomeworkDescriptionLong().getValue(),  designerView.getHomeworkDescription().getValue(),
                            zdt);
                    manager.create(h);

                }


                resetAfterSave();


            }
        });

    }

    void setupComobobox()
    {

        designerView.getCombobox().setItems("Übung",
                    "Dokument", "Fragebogen");
        designerView.getCombobox().setValue("Übung");

    }

    void setUpDataPicker()
    {
        designerView.getDataPicker().setDefaultValue(LocalDate.from(LocalDateTime.now()));
        designerView.getDataPicker().setRangeStart(LocalDate.from(LocalDateTime.now()));


    }


    void addTextBoxRestrictions()
    {

designerView.getHomeworkDescriptionLong().setMaxLength(120);
designerView.getHomeworkDescription().setMaxLength(25);
designerView.getHomeworkName().setMaxLength(12);



    }



    boolean isDateOccupied(ZonedDateTime t)
    {
        LocalDate local= designerView.getDataPicker().getValue();
        ZonedDateTime zdt = local.atStartOfDay(ZoneOffset.UTC);
        HomeworkManager manager = new HomeworkManager();

        List<Homework> homeworkList = manager.read();
        for(Homework w: homeworkList)
        {
            if(zdt == w.getDate())
            {
               return true;

            }


        }

        return false;


    }


    void resetAfterSave()
    {


        designerView.getHomeworkName().setValue("");
        designerView.getHomeworkDescription().setValue("");
        designerView.getHomeworkDescriptionLong().setValue("");
        designerView.getDataPicker().setValue(null);
        designerView.getSaveButton().setEnabled(false);
        loadCalendarEntries();

        designerView.getCalendar().setData(basicProvider);

        designerView.getUI().access(new Runnable() {
            @Override
            public void run() {

                designerView.getUI().getNavigator().navigateTo("Hausaufgaben");


            }
        });


    }




}
