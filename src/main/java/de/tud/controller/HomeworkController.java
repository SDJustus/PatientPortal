package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
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
import de.tud.view.Homework.FinishedItem;
import de.tud.view.Homework.HomeworkDesigner;
import de.tud.view.Homework.HomeworkSetup;
import de.tud.view.Homework.ToDoItem;
import de.tud.view.Welfare.WelfareUISetup;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.handler.BasicItemMoveHandler;
import org.vaadin.addon.calendar.item.BasicItem;
import org.vaadin.addon.calendar.item.BasicItemProvider;
import org.vaadin.addon.calendar.item.CalendarItemProvider;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import javax.xml.crypto.Data;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HomeworkController {


    HomeworkSetup designerView;
    private long diaryId;


    BasicItemProvider<BasicItem> basicProvider;
    private String repeat;


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

        designerView.getCalendar().withVisibleDays(1, 7);
         // designerView.getCalendar().withMonth(ZonedDateTime.now().getMonth());

        //designerView.getCalendar().setStartDate(ZonedDateTime.of(2017, 9, 10, 0,0,0, 0, designerView.getCalendar().getZoneId()));
        // designerView.getCalendar().setEndDate(ZonedDateTime.of(2050, 9, 16, 0,0,0, 0, designerView.getCalendar().getZoneId()));
       // setupBlockedTimeSlots();



        loadCalendarEntries();



    }




    public void setupBlockedTimeSlots() {


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


   public void loadCalendarEntries()
    {

        try{HomeworkManager.getInstance().read();
        }catch (NullPointerException e){
            e.getMessage();
            return;
        }

        List<Homework> homeworkList = HomeworkManager.getInstance().read();

       CalendarItemProvider<BasicItem> provider;

        basicProvider = new BasicItemProvider<>();





        for(Homework home:homeworkList)
        {

            if(home == null)
            {
                break;

            }
            if(home.isStatus() == false)
            {
                ToDoItem basic = new ToDoItem(home.getId());
                basic.setStart(home.getDate());
                basic.setEnd(home.getDate().plusHours(2));

                basic.setDescription(home.getType().toString()+": "+home.getDescription());
                basic.setCaption(home.getName());
                basicProvider.addItem(basic);


            }


            if(home.isStatus()==true)
            {
                FinishedItem basic = new FinishedItem(home.getId());
                basic.setStart(home.getDate());
                basic.setEnd(home.getDate().plusHours(1));

                basic.setDescription(home.getType().toString()+": "+home.getDescription());
                basic.setCaption(home.getName());
                basicProvider.addItem(basic);


            }


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

                if(designerView.getHomeworkName().isEmpty() == true)
                {
                    Notification.show("Bitte füllen Sie alle Felder aus");
                    return;
                }


                Homework h = new Homework();
                LocalDate local= designerView.getDataPicker().getValue();
                ZonedDateTime zdt = local.atStartOfDay(ZoneOffset.UTC);
                ZonedDateTime now = ZonedDateTime.now();




                List<Homework> homeworkList = HomeworkManager.getInstance().read();

                    if(isDateOccupied(zdt) == true)
                    {
                        Notification.show("Datum und Uhrzeit bereits belegt");
                        designerView.getDataPicker().setValue(null);
                        designerView.getSaveButton().setEnabled(false);
                        return;
                    }



            if(designerView.getRepeatBox().getValue().equals("Einmalig")) {


                    HomeworkManager.getInstance().create(createHomeworkFromUI(zdt));


            }


              if(designerView.getRepeatBox().getValue().equals("Täglich"))
                {




                    ZonedDateTime zonedTime = designerView.getDataPicker().getValue().atStartOfDay(ZoneOffset.UTC);

                    System.out.println(zonedTime);

                    int dayDiff = (int) now.until(zonedTime, ChronoUnit.DAYS);
                    System.out.println("DayDiff:");

                        System.out.println(dayDiff);

                    System.out.println("Now+1: ");
                        System.out.println(now.plusDays(1));
                        for(int i =0; i<= dayDiff ; i++)
                        {

                            HomeworkManager.getInstance().create(createHomeworkFromUI(now));
                            now = now.plusDays(1);

                        }

                }


                if(designerView.getRepeatBox().getValue().equals("Wöchentlich bis Endtermin"))
                {

                    ZonedDateTime zonedTime = designerView.getDataPicker().getValue().atStartOfDay(ZoneOffset.UTC);

                    System.out.println(zonedTime);

                    int dayDiff = (int) now.until(zonedTime, ChronoUnit.WEEKS);
                    for(int i =0; i<= dayDiff ; i++)
                    {

                        HomeworkManager.getInstance().create(createHomeworkFromUI(now));
                        now = now.plusDays(7);
                    }

                }

                resetAfterSave();


            }
        });

    }

   public void setupComobobox()
    {

        designerView.getCombobox().setItems("Übung",
                    "Dokument", "Fragebogen");
        designerView.getCombobox().setValue("Übung");

    }

   public void setUpDataPicker()
    {
        designerView.getDataPicker().setDefaultValue(LocalDate.from(LocalDateTime.now()));
        designerView.getDataPicker().setRangeStart(LocalDate.from(LocalDateTime.now()));


    }


  public  void addTextBoxRestrictions()
    {


designerView.getHomeworkDescription().setMaxLength(120);
designerView.getHomeworkName().setMaxLength(12);



    }



    public boolean isDateOccupied(ZonedDateTime t)
    {
        LocalDate local= designerView.getDataPicker().getValue();
        ZonedDateTime zdt = local.atStartOfDay(ZoneOffset.UTC);

        List<Homework> homeworkList = HomeworkManager.getInstance().read();
        for(Homework w: homeworkList)
        {
            if(zdt == w.getDate())
            {
               return true;

            }


        }

        return false;


    }


    public void resetAfterSave()
    {


        designerView.getHomeworkName().setValue("");
        designerView.getHomeworkDescription().setValue("");
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



   public void addCalenderListenerForCaptionLabel()

   {


        
        LocalDate startDate = designerView.getCalendar().getStartDate().toLocalDate();
        LocalDate endDate = designerView.getCalendar().getEndDate().toLocalDate();
        String start = startDate.getDayOfMonth()+"."+startDate.getMonthValue()+"."+startDate.getYear();
        String end = endDate.getDayOfMonth()+"."+endDate.getMonthValue()+"."+endDate.getYear();
        designerView.getCalenderLabel().setValue(start + " - " + end);



    }




Homework createHomeworkFromUI(ZonedDateTime now)
    {
            Homework h = new Homework();
        if (designerView.getCombobox().getValue() == "Fragebogen") {

            h = new Homework(Homework.Type.QUESTIONNAIRE, designerView.getHomeworkName().getValue()
                    , designerView.getHomeworkDescription().getValue(),
                    now);


        }
        if (designerView.getCombobox().getValue() == "Übung") {

            h = new Homework(Homework.Type.EXERCISE, designerView.getHomeworkName().getValue(), designerView.getHomeworkDescription().getValue(),
                    now);

        }
        if (designerView.getCombobox().getValue() == "Dokument") {
            h = new Homework(Homework.Type.DOCUMENT, designerView.getHomeworkName().getValue(),
                    designerView.getHomeworkDescription().getValue(),
                    now);

        }


        return h;



    }









}


