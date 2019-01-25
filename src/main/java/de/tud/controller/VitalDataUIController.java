package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.view.vitalData.VitalDataView;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class VitalDataUIController  {

    VitalDataView desview;
    private long diaryId;
    Diary diary;




    /**
     * Initiate a new controller object.
     */

   public  VitalDataUIController(VitalDataView desview)
    {
       this.desview = desview;
        diary = DiaryManager.getInstance().read().get(0);
        diaryId = diary.getId();
        this.desview.getSaveVitalData().setEnabled(false);


    }


    /**
     * Adds a safe button listener and restrictions.
     */


    public void addSaveButtonListener(){
        desview.getSaveVitalData().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(desview.getDataPicker().getValue() == null){
                    Notification.show("Bitte Datum eingeben!");
                    desview.getSaveVitalData().setEnabled(false);
                    return;
                }

                VitalData data = new VitalData();
                data.setBloodPressureFirstValue(desview.getBloodPressureStepper1().getValue().intValue());
                data.setBloodPressureSecondValue(desview.getBloodPressureStepper11().getValue().intValue());
                data.setHeartRate(desview.getHeartRateStepper().getValue().intValue());
                data.setHeight(desview.getHeightStepper().getValue());
                data.setWeight(desview.getWeightStepper().getValue());


                saveVitalDataDiaryEntry(desview.getDataPicker().getValue(), data);
                Notification.show("Eintrag erfolgreich gespeichert");

            }
        });
    }


    /**
     * Saves the entry to the database.
     */

    public void saveVitalDataDiaryEntry(LocalDateTime datum, VitalData data){

        Diary diary = DiaryManager.getInstance().read().get(0);
        double diaryId = diary.getId();

        DiaryEntry diaryEntry = new DiaryEntry(datum, data);  //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        DiaryManager.getInstance().addDiaryEntry(diaryEntry,(long) diaryId);

    }

    /**
     * Adds a listener to the data picker.
     */

    public void addDateTimeFieldChangeListener(){
        desview.getDataPicker().addValueChangeListener(new HasValue.ValueChangeListener<LocalDateTime>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<LocalDateTime> valueChangeEvent) {
                if(desview.getDataPicker().getValue() != null ){
                    desview.getSaveVitalData().setEnabled(true);
                }
            }
        });

    }


}
