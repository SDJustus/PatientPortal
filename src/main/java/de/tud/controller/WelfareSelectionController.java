package de.tud.controller;

import com.vaadin.event.MouseEvents;
import de.tud.model.welfare.Welfare;
import de.tud.model.welfare.WelfareFactory;
import de.tud.view.Welfare.WelfareSelectionView;


public class WelfareSelectionController {

    private WelfareSelectionView welfareSelectionView;
    private Welfare.Strength choice;
    private Welfare welfareArt;
    private  WelfareController welfareController;


    public WelfareSelectionController(WelfareSelectionView selectionView, WelfareController welfareController){
        this.welfareSelectionView = selectionView;
        this.welfareController = welfareController;

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
                    checkSaveButton();


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
                    checkSaveButton();


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
                    checkSaveButton();
            }
        });
    }
    private void checkSaveButton(){
        for(WelfareSelectionController s : welfareController.getWelfareSelectionControllers()){
            if(s.getChoice() == null){
                welfareController.getWelfareView().getSave().setEnabled(false);
                return;
            }
        }
        if(welfareController.checkDateIntegrity()) {
            welfareController.getWelfareView().getSave().setEnabled(true);
        }

    }
    public Welfare.Strength getChoice() {
        return choice;
    }
    public Welfare getWelfareArt() {
        return welfareArt;
    }


}
