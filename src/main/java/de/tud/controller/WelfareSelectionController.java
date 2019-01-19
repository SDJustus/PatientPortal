package de.tud.controller;

import com.vaadin.event.MouseEvents;
import de.tud.model.welfare.Welfare;
import de.tud.model.welfare.WelfareFactory;
import de.tud.view.Welfare.WelfareSelectionView;

import java.util.ArrayList;
import java.util.List;

public class WelfareSelectionController {

    private WelfareSelectionView welfareSelectionView;
    private Welfare.Strength choice;
    private Welfare welfareArt;


    public WelfareSelectionController(
            WelfareSelectionView selectionView){
        this.welfareSelectionView = selectionView;

    }

    public void addClickListenerForSmileys(){
        welfareSelectionView.getGoodSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                    welfareSelectionView.getMiddleLabel().setValue("");
                    welfareSelectionView.getBadLabel().setValue("");
                    welfareSelectionView.getGoodLabel().setValue("schwach");
                    choice = Welfare.Strength.WEAK;
                    welfareArt =  WelfareFactory.getInstance()
                        .createSymptomByClass(welfareSelectionView.getWelfare(), choice);

                    welfareSelectionView.getGoodSmiley().setId("");
                    welfareSelectionView.getMiddleSmiley().setId("greyscale");
                    welfareSelectionView.getBadSmiley().setId("greyscale");

            }
        });
        welfareSelectionView.getMiddleSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                    welfareSelectionView.getGoodLabel().setValue("");
                    welfareSelectionView.getBadLabel().setValue("");
                    welfareSelectionView.getMiddleLabel().setValue("mäßig");
                    choice = Welfare.Strength.MIDDLE;
                    welfareArt =  WelfareFactory.getInstance()
                            .createSymptomByClass(welfareSelectionView.getWelfare(), choice);

                    welfareSelectionView.getMiddleSmiley().setId("");
                    welfareSelectionView.getGoodSmiley().setId("greyscale");
                    welfareSelectionView.getBadSmiley().setId("greyscale");


            }
        });
        welfareSelectionView.getBadSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {

                    welfareSelectionView.getGoodLabel().setValue("");
                    welfareSelectionView.getMiddleLabel().setValue("");
                    welfareSelectionView.getBadLabel().setValue("stark");
                    choice = Welfare.Strength.SEVERE;
                 welfareArt =  WelfareFactory.getInstance()
                        .createSymptomByClass(welfareSelectionView.getWelfare(), choice);

                    welfareSelectionView.getBadSmiley().setId("");
                    welfareSelectionView.getGoodSmiley().setId("greyscale");
                    welfareSelectionView.getMiddleSmiley().setId("greyscale");



            }
        });
    }
    public Welfare.Strength getChoice() {
        return choice;
    }
    public Welfare getWelfareArt() {
        return welfareArt;
    }


}