package de.tud.controller;

import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.view.WelfareViewNew;

public class WelfareControllerNew {

    private WelfareViewNew welfareViewNew;

    public WelfareControllerNew(WelfareViewNew welfareViewNew) {

        this.welfareViewNew = welfareViewNew;

    }

    public void addSaveButtonListener() {

        welfareViewNew.getSaveButton().addClickListener((Button.ClickListener) clickEvent -> {

            Notification.show("SaveButtonClicked");

        });

    }

    public void addResetButtonListener() {

        welfareViewNew.getResetButton().addClickListener((Button.ClickListener) clickEvent -> {

            Notification.show("ResetButtonClicked");

        });

    }

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

            }});

    }

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

            }});

    }

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

            }});

    }

}
