package de.tud.controller;


import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.view.VitalData.VitalDataUIDesignerUISetup;
import de.tud.view.Welfare.WelfareUISetup;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class WelfareController  {




    WelfareUISetup desview;
    private long diaryId;
    DiaryManager diaryManager;
    Diary diary;





    public  WelfareController(WelfareUISetup desview)
    {
        this.desview = desview;
        diaryManager = new DiaryManager();
        diary = diaryManager.read().get(0);
        diaryId = diary.getId();
        this.desview.getSave().setEnabled(false);


    }

    public void saveVitalDataEntry(LocalDateTime datum, Set<Symptom> symptoms){




        DiaryEntry diaryEntry = new DiaryEntry(datum , symptoms,new VitalData(), new HashSet<>());        //TODO: Replace "new VitalDaraSet","new HashSet" - it is only a placeholder
        diaryManager.addDiaryEntry(diaryEntry, diaryId);

    }


    public void checkSaveButton(){

        if(desview.getDataPicker().getValue() != null){
            desview.getSave().setEnabled(true);
        }

    }
    private void integrityRestrictionsDateTimeField(){
        //TODO: Integritätsbedingungen in DiaryViewController
        desview.getDataPicker().setRangeStart(LocalDateTime.now().minusWeeks(3));
        desview.getDataPicker().setRangeEnd(LocalDateTime.now().plusMinutes(1));
    }


    public void addSaveButtonListener(){
        desview.getSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(desview.getDataPicker().getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    desview.getSave().setEnabled(false);
                    return;
                }


                // saveVitalDataDiaryEntry(desview.getDataPicker().getValue(), data);
                Notification.show("Eintrag erfolgreich gespeichert");

            }
        });
    }


    public void saveVitalDataDiaryEntry(LocalDateTime datum, VitalData data){
        DiaryManager diaryManager = new DiaryManager();
        Diary diary = diaryManager.read().get(0);
        double diaryId = diary.getId();

        DiaryEntry diaryEntry = new DiaryEntry(datum, data);  //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        DiaryManager.getInstance().addDiaryEntry(diaryEntry,(long) diaryId);

    }

    public void addDateTimeFieldChangeListener(){
        desview.getDataPicker().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
                if(desview.getDataPicker().getValue() != null ){
                    desview.getSave().setEnabled(true);
                }
            }
        });

    }

}








