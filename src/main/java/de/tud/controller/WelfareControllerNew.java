package de.tud.controller;

import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Notification;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.manager.DiaryManager;
import de.tud.model.welfare.Welfare;
import de.tud.model.welfare.WelfareFactory;
import de.tud.view.Welfare.WelfareViewNew;

import java.time.LocalDateTime;
import java.util.HashSet;

public class WelfareControllerNew {

    private WelfareViewNew welfareViewNew;
    private String concentrationChoice = "none";
    private String fitnessChoice = "none";
    private String sleepChoice = "none";
    private long diaryId;
    DiaryManager diaryManager;
    Diary diary;
    HashSet<Welfare> welfareSet;

    /**
     * Initiate a welfare view.
     */

    public WelfareControllerNew(WelfareViewNew welfareViewNew) {

        this.welfareViewNew = welfareViewNew;

    }


    /**
     * Adds a listener to the data picker.
     */
    public void addDateFieldListener() {

        welfareViewNew.getDateField().addValueChangeListener((DateTimeField.ValueChangeListener) valueChangeEvent -> {

            if (concentrationChoice != "none" && fitnessChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

        });

    }

    /**
     * Adds a listener to the save button.
     */
    public void addSaveButtonListener() {

        welfareViewNew.getSaveButton().addClickListener((Button.ClickListener) clickEvent -> {

            addWelfare();
            saveWelfareDiaryEntry(welfareViewNew.getDateField().getValue(), welfareSet);
            Notification.show("Eintrag erfolgreich gespeichert");

        });

    }
    /**
     * Adds a listener for the reset button.
     */

    public void addResetButtonListener() {

        welfareViewNew.getResetButton().addClickListener((Button.ClickListener) clickEvent -> {

            welfareViewNew.getcSmileyGood().setId("");
            welfareViewNew.getcSmileyMiddle().setId("");
            welfareViewNew.getcSmileyBad().setId("");
            welfareViewNew.getfSmileyGood().setId("");
            welfareViewNew.getfSmileyMiddle().setId("");
            welfareViewNew.getfSmileyBad().setId("");
            welfareViewNew.getsSmileyGood().setId("");
            welfareViewNew.getsSmileyMiddle().setId("");
            welfareViewNew.getsSmileyBad().setId("");
            welfareViewNew.getcLabelGood().setValue("Gut");
            welfareViewNew.getcLabelMiddle().setValue("Mäßig");
            welfareViewNew.getcLabelBad().setValue("Schlecht");
            welfareViewNew.getfLabelGood().setValue("Gut");
            welfareViewNew.getfLabelMiddle().setValue("Mäßig");
            welfareViewNew.getfLabelBad().setValue("Schlecht");
            welfareViewNew.getsLabelGood().setValue("Gut");
            welfareViewNew.getsLabelMiddle().setValue("Mäßig");
            welfareViewNew.getsLabelBad().setValue("Schlecht");
            welfareViewNew.getDateField().clear();
            concentrationChoice = "none";
            fitnessChoice = "none";
            sleepChoice = "none";
            welfareViewNew.getSaveButton().setEnabled(false);

        });

    }

    /**
     * Adds a listener for  the interaction with smileys.
     */

    public void addcSmileyClickListener() {

        welfareViewNew.getcSmileyGood().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getcSmileyGood().setId("");
                welfareViewNew.getcSmileyMiddle().setId("greyscale");
                welfareViewNew.getcSmileyBad().setId("greyscale");
                welfareViewNew.getcLabelGood().setValue("Gut");
                welfareViewNew.getcLabelMiddle().setValue("");
                welfareViewNew.getcLabelBad().setValue("");
                concentrationChoice = "good";
                if (fitnessChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

        }});

        welfareViewNew.getcSmileyMiddle().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getcSmileyGood().setId("greyscale");
                welfareViewNew.getcSmileyMiddle().setId("");
                welfareViewNew.getcSmileyBad().setId("greyscale");
                welfareViewNew.getcLabelGood().setValue("");
                welfareViewNew.getcLabelMiddle().setValue("Mäßig");
                welfareViewNew.getcLabelBad().setValue("");
                concentrationChoice = "middle";
                if (fitnessChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

        welfareViewNew.getcSmileyBad().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getcSmileyGood().setId("greyscale");
                welfareViewNew.getcSmileyMiddle().setId("greyscale");
                welfareViewNew.getcSmileyBad().setId("");
                welfareViewNew.getcLabelGood().setValue("");
                welfareViewNew.getcLabelMiddle().setValue("");
                welfareViewNew.getcLabelBad().setValue("Schlecht");
                concentrationChoice = "bad";
                if (fitnessChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

    }


    /**
     * Adds a listener for  the interaction with smileys.
     */
    public void addfSmileyClickListener() {

        welfareViewNew.getfSmileyGood().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getfSmileyGood().setId("");
                welfareViewNew.getfSmileyMiddle().setId("greyscale");
                welfareViewNew.getfSmileyBad().setId("greyscale");
                welfareViewNew.getfLabelGood().setValue("Gut");
                welfareViewNew.getfLabelMiddle().setValue("");
                welfareViewNew.getfLabelBad().setValue("");
                fitnessChoice = "good";
                if (concentrationChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

        welfareViewNew.getfSmileyMiddle().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getfSmileyGood().setId("greyscale");
                welfareViewNew.getfSmileyMiddle().setId("");
                welfareViewNew.getfSmileyBad().setId("greyscale");
                welfareViewNew.getfLabelGood().setValue("");
                welfareViewNew.getfLabelMiddle().setValue("Mäßig");
                welfareViewNew.getfLabelBad().setValue("");
                fitnessChoice = "middle";
                if (concentrationChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

        welfareViewNew.getfSmileyBad().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getfSmileyGood().setId("greyscale");
                welfareViewNew.getfSmileyMiddle().setId("greyscale");
                welfareViewNew.getfSmileyBad().setId("");
                welfareViewNew.getfLabelGood().setValue("");
                welfareViewNew.getfLabelMiddle().setValue("");
                welfareViewNew.getfLabelBad().setValue("Schlecht");
                fitnessChoice = "bad";
                if (concentrationChoice != "none" && sleepChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

    }

    /**
     * Adds a listener for  the interaction with smileys.
     */
    public void addsSmileyClickListener() {

        welfareViewNew.getsSmileyGood().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getsSmileyGood().setId("");
                welfareViewNew.getsSmileyMiddle().setId("greyscale");
                welfareViewNew.getsSmileyBad().setId("greyscale");
                welfareViewNew.getsLabelGood().setValue("Gut");
                welfareViewNew.getsLabelMiddle().setValue("");
                welfareViewNew.getsLabelBad().setValue("");
                sleepChoice = "good";
                if (concentrationChoice != "none" && fitnessChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

        welfareViewNew.getsSmileyMiddle().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getsSmileyGood().setId("greyscale");
                welfareViewNew.getsSmileyMiddle().setId("");
                welfareViewNew.getsSmileyBad().setId("greyscale");
                welfareViewNew.getsLabelGood().setValue("");
                welfareViewNew.getsLabelMiddle().setValue("Mäßig");
                welfareViewNew.getsLabelBad().setValue("");
                sleepChoice = "middle";
                if (concentrationChoice != "none" && fitnessChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

        welfareViewNew.getsSmileyBad().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                welfareViewNew.getsSmileyGood().setId("greyscale");
                welfareViewNew.getsSmileyMiddle().setId("greyscale");
                welfareViewNew.getsSmileyBad().setId("");
                welfareViewNew.getsLabelGood().setValue("");
                welfareViewNew.getsLabelMiddle().setValue("");
                welfareViewNew.getsLabelBad().setValue("Schlecht");
                sleepChoice = "bad";
                if (concentrationChoice != "none" && fitnessChoice != "none" && welfareViewNew.getDateField().getValue() != null) {welfareViewNew.getSaveButton().setEnabled(true);}

            }});

    }

    /**
     * Creates a new welfare set.
     */


    public void addWelfare() {

        welfareSet = new HashSet<>();
        if (concentrationChoice == "good") { welfareSet.add(WelfareFactory.createSymptomByClass( "ConcentrationAbility", Welfare.Strength.WEAK)); }
        if (concentrationChoice == "middle") { welfareSet.add(WelfareFactory.createSymptomByClass( "ConcentrationAbility", Welfare.Strength.MIDDLE)); }
        if (concentrationChoice == "bad") { welfareSet.add(WelfareFactory.createSymptomByClass( "ConcentrationAbility", Welfare.Strength.SEVERE)); }
        if (fitnessChoice == "good") { welfareSet.add(WelfareFactory.createSymptomByClass( "PhysicalCondition", Welfare.Strength.WEAK)); }
        if (fitnessChoice == "middle") { welfareSet.add(WelfareFactory.createSymptomByClass( "PhysicalCondition", Welfare.Strength.MIDDLE)); }
        if (fitnessChoice == "bad") { welfareSet.add(WelfareFactory.createSymptomByClass( "PhysicalCondition", Welfare.Strength.SEVERE)); }
        if (sleepChoice == "good") { welfareSet.add(WelfareFactory.createSymptomByClass( "Sleep", Welfare.Strength.WEAK)); }
        if (sleepChoice == "middle") { welfareSet.add(WelfareFactory.createSymptomByClass( "Sleep", Welfare.Strength.MIDDLE)); }
        if (sleepChoice == "bad") { welfareSet.add(WelfareFactory.createSymptomByClass( "Sleep", Welfare.Strength.SEVERE)); }

    }

    /**
     * Saves the entry to the data base.
     */

    public void saveWelfareDiaryEntry(LocalDateTime datum, HashSet<Welfare> data) {

        DiaryManager diaryManager = DiaryManager.getInstance();
        Diary diary = diaryManager.read().get(0);
        long diaryId = diary.getId();
        DiaryEntry diaryEntry = new DiaryEntry(datum, data);
        DiaryManager.getInstance().addDiaryEntry(diaryEntry, diaryId);
        return;

    }

}
