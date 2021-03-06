package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import de.tud.view.homework.FinishedItem;
import de.tud.view.homework.HomeworkSetup;
import de.tud.view.homework.ToDoItem;
import org.vaadin.addon.calendar.item.BasicItem;
import org.vaadin.addon.calendar.item.BasicItemProvider;
import org.vaadin.addon.calendar.item.CalendarItemProvider;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeworkController {


    /**
     * This variable grants access to the UI.
     */
    HomeworkSetup designerView;




    private final Logger LOGGER = Logger.getLogger(HomeworkController.class.getSimpleName());

    /**
     * Data Structure for UI calendar items.
     */

    BasicItemProvider<BasicItem> basicProvider;


    /**
     * Setup for the calender zu element.
     */
    public  HomeworkController(HomeworkSetup designerView)
    {
        this.designerView = designerView;
        this.designerView.getSaveButton().setEnabled(false);
        setUpCalendar();
        setupComobobox();
        setUpDataPicker();
        addTextBoxRestrictions();

    }




    /**
     * Sets properties for the calendar.
     */

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

    /**
     * Loads all data base entries into the calendar.
     */

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

    /**
     * Adds listener for date picker element.
     */
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


    /**
     * Adds listener and save function to the save button.
     */

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

                    LOGGER.log(Level.INFO, zonedTime.toString());

                    int dayDiff = (int) now.until(zonedTime, ChronoUnit.DAYS);
                    LOGGER.log(Level.INFO, "DayDiff:");

                        LOGGER.log(Level.INFO, String.valueOf(dayDiff));

                    LOGGER.log(Level.INFO, "Now+1: ");
                        LOGGER.log(Level.INFO, now.plusDays(1).toString());
                        for(int i =0; i<= dayDiff ; i++)
                        {

                            HomeworkManager.getInstance().create(createHomeworkFromUI(now));
                            now = now.plusDays(1);

                        }

                }


                if(designerView.getRepeatBox().getValue().equals("Wöchentlich bis Endtermin"))
                {

                    ZonedDateTime zonedTime = designerView.getDataPicker().getValue().atStartOfDay(ZoneOffset.UTC);

                    LOGGER.log(Level.INFO, zonedTime.toString());

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

    /**
     * Adds task types to the combobox.
     */

   public void setupComobobox()
    {

        designerView.getCombobox().setItems("Übung",
                    "Dokument", "Fragebogen");
        designerView.getCombobox().setValue("Übung");

    }

    /**
     * Adds restrictions for the date picker.
     */

   public void setUpDataPicker()
    {
        designerView.getDataPicker().setDefaultValue(LocalDate.from(LocalDateTime.now()));
        designerView.getDataPicker().setRangeStart(LocalDate.from(LocalDateTime.now()));


    }


    /**
     * Adds restrictions for both text boxes on the homework view.
     */

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


    /**
     * Reloads the homework view.
     * Use this in combination with saving.
     */

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


    /**
     * Unused function for a not implemented element.
     */
   public void addCalenderListenerForCaptionLabel()

   {


        
        LocalDate startDate = designerView.getCalendar().getStartDate().toLocalDate();
        LocalDate endDate = designerView.getCalendar().getEndDate().toLocalDate();
        String start = startDate.getDayOfMonth()+"."+startDate.getMonthValue()+"."+startDate.getYear();
        String end = endDate.getDayOfMonth()+"."+endDate.getMonthValue()+"."+endDate.getYear();
        designerView.getCalenderLabel().setValue(start + " - " + end);



    }



    /**
     * Creates new homework objects and saves them to the database.
     */


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


