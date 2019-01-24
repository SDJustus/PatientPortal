package de.tud.controller;


import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.ConcentrationAbility;
import de.tud.model.welfare.PhysicalCondition;
import de.tud.model.welfare.Sleep;
import de.tud.model.welfare.Welfare;
import de.tud.view.VitalData.VitalDataView;
import de.tud.view.Welfare.WelfareSelectionView;
import de.tud.view.Welfare.WelfareView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WelfareController  {


    private WelfareView welfareView;
    private long diaryId;
    private DiaryManager diaryManager;
    private Diary diary;


    private List<WelfareSelectionController> welfareSelectionControllers = new ArrayList<>();


    public  WelfareController(WelfareView welfareView)
    {
        this.welfareView = welfareView;
        diaryManager = DiaryManager.getInstance();
        diary = diaryManager.read().get(0);
        diaryId = diary.getId();
    }

    /*
    public void checkSaveButton(){
        if(welfareView.getDateTimeField().getValue() != null){
            welfareView.getSave().setEnabled(true);
        }

    }*/

    private void integrityRestrictionsDateTimeField(){
        //TODO: Integritätsbedingungen in DiaryViewController
        welfareView.getDateTimeField().setRangeStart(LocalDateTime.now().minusWeeks(3));
        welfareView.getDateTimeField().setRangeEnd(LocalDateTime.now().plusMinutes(1));
    }

    //TODO Outsource
    public boolean checkDateIntegrity(){
        LocalDateTime rangeEnd =  welfareView.getDateTimeField().getRangeEnd();
        System.out.println(rangeEnd);
        LocalDateTime rangeStart = welfareView.getDateTimeField().getRangeStart();
        LocalDateTime date = welfareView.getDateTimeField().getValue();
        if(date.isAfter(rangeEnd) || date.isBefore(rangeStart)){
            Notification.show("unzulässiges Datum gewählt!");
            return false;
        }
        return true;
    }

    public void addSaveButtonListener(){
        welfareView.getSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                if(welfareSelectionControllers.size() == 0){
                    return;
                }
                if(!checkDateIntegrity()){
                    return;
                }
                if(welfareView.getDateTimeField().getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    welfareView.getSave().setEnabled(false);
                    return;
                }

                HashSet<Welfare> welfareHashSet = new HashSet<>();
                for(WelfareSelectionController welfareSelectionController : welfareSelectionControllers){
                    welfareHashSet.add(welfareSelectionController.getWelfareArt());
                    if(welfareSelectionController.getWelfareArt() == null ||
                            welfareSelectionController.getChoice() == null){
                        Notification.show("Es fehlen noch Eingaben.", Notification.Type.HUMANIZED_MESSAGE);
                        return;
                    }
                }
                welfareView.getSave().setEnabled(false);
                saveWelfareDiaryEntry(welfareView.getDateTimeField().getValue(), welfareHashSet);
                Notification.show("Eintrag erfolgreich gespeichert");

            }
        });
    }


    public void saveWelfareDiaryEntry(LocalDateTime datum, HashSet<Welfare> data){
        DiaryManager diaryManager = DiaryManager.getInstance();
        Diary diary = diaryManager.read().get(0);
        long diaryId = diary.getId();

        DiaryEntry diaryEntry = new DiaryEntry(datum, data);
        DiaryManager.getInstance().addDiaryEntry(diaryEntry, diaryId);
        welfareView.getSave().setEnabled(true);
    }

    public void addDateTimeFieldChangeListener(){
        welfareView.getDateTimeField().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {

                //TODO
                if(welfareView.getDateTimeField().getValue() != null && checkDateIntegrity() && checkWelfareSelection() ) {
                    welfareView.getSave().setEnabled(true);
                }

            }
        });

    }

    public boolean checkWelfareSelection(){
        List<Boolean> list = new ArrayList<>();

        for(WelfareSelectionController s: welfareSelectionControllers){
            list.add(s.getWelfareArt() == null);
        }
        if(list.contains(true)){
            return false;
        }
        return true;
    }

    public void initWelfare(){

         WelfareSelectionView sleep = new WelfareSelectionView(Sleep.class.getSimpleName(), this);
         WelfareSelectionView fitness = new WelfareSelectionView(PhysicalCondition.class.getSimpleName(), this);
         WelfareSelectionView concentration = new WelfareSelectionView(ConcentrationAbility.class.getSimpleName(), this);

         welfareSelectionControllers.add(sleep.getWelfareSelectionController());
         welfareSelectionControllers.add(fitness.getWelfareSelectionController());
         welfareSelectionControllers.add(concentration.getWelfareSelectionController());

       welfareView.getContentLayout()
               .addComponents(sleep.getViewComponent(), fitness.getViewComponent(),
                       concentration.getViewComponent());


        integrityRestrictionsDateTimeField();
        welfareView.getDateTimeField().setValue(LocalDateTime.now());
        welfareView.getSave().setEnabled(false);

    }

    public List<WelfareSelectionController> getWelfareSelectionControllers() {
        return welfareSelectionControllers;
    }
    public WelfareView getWelfareView() {
        return welfareView;
    }

    public void addResetButtonListener(){
        welfareView.getResetButton().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                welfareView.getContentLayout().removeAllComponents();
                welfareSelectionControllers.clear();
                initWelfare();
            }
        });
    }

}












