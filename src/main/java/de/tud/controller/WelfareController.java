package de.tud.controller;


import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.Welfare;
import de.tud.view.VitalData.VitalDataView;
import de.tud.view.Welfare.WelfareSelectionView;
import de.tud.view.Welfare.WelfareView;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class WelfareController  {


    private WelfareView welfareView;
    private long diaryId;
    private DiaryManager diaryManager;
    private Diary diary;
    private HashSet<Welfare> welfareSet;






    public  WelfareController(WelfareView welfareView)
    {
        this.welfareView = welfareView;
        /*
        diaryManager = DiaryManager.getInstance();
        diary = diaryManager.read().get(0);
        diaryId = diary.getId();
        */



    }
    /*
    public void saveVitalDataEntry(LocalDateTime datum, Set<Symptom> symptoms){

        DiaryEntry diaryEntry = new DiaryEntry(datum , symptoms,new VitalData(), new HashSet<>());        //TODO: Replace "new VitalDaraSet","new HashSet" - it is only a placeholder
        diaryManager.addDiaryEntry(diaryEntry, diaryId);

    }


    public void PushßtoßtalkcheckSaveButton(){

        if(welfareView.getDateTimeField().getValue() != null){
            welfareView.getSave().setEnabled(true);
        }

    }
    private void integrityRestrictionsDateTimeField(){
        //TODO: Integritätsbedingungen in DiaryViewController
        welfareView.getDateTimeField().setRangeStart(LocalDateTime.now().minusWeeks(3));
        welfareView.getDateTimeField().setRangeEnd(LocalDateTime.now().plusMinutes(1));
    }


    public void addSaveButtonListener(){
        welfareView.getSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


            }
        });
    }


    public void saveWelfareDiaryEntry(LocalDateTime datum, HashSet<Welfare> data){
        DiaryManager diaryManager = DiaryManager.getInstance();
        Diary diary = diaryManager.read().get(0);
        long diaryId = diary.getId();

        DiaryEntry diaryEntry = new DiaryEntry(datum, data);
        DiaryManager.getInstance().addDiaryEntry(diaryEntry, diaryId);
        return;
    }

    public void addDateTimeFieldChangeListener(){
        welfareView.getDateTimeField().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {


            }
        });

    }
    */

    public void initWelfare(){

         WelfareSelectionView sleep = new WelfareSelectionView("sleep");
         WelfareSelectionView fitness = new WelfareSelectionView("fitness");
         WelfareSelectionView concentration = new WelfareSelectionView("concentration");

       welfareView.getContentLayout()
               .addComponents(sleep.getViewComponent(), fitness.getViewComponent(),
                       concentration.getViewComponent());

    }





}












