package de.tud.controller;


import com.vaadin.data.HasValue;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.ConcentrationAbility;
import de.tud.model.welfare.Welfare;
import de.tud.model.welfare.WelfareFactory;
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
    HashSet<Welfare> welfareSet;

    ClassResource good;
    ClassResource middle;
    ClassResource bad;




    public  WelfareController(WelfareUISetup desview)
    {
        this.desview = desview;
        diaryManager = DiaryManager.getInstance();
        diary = diaryManager.read().get(0);
        diaryId = diary.getId();
        this.desview.getSave().setEnabled(false);

        good = new ClassResource("/gut.png");
        middle = new ClassResource("/mittel.png");
        bad = new ClassResource("/schlecht.png");

    }

    public void saveVitalDataEntry(LocalDateTime datum, Set<Symptom> symptoms){




        DiaryEntry diaryEntry = new DiaryEntry(datum , symptoms,new VitalData(), new HashSet<>());        //TODO: Replace "new VitalDaraSet","new HashSet" - it is only a placeholder
        diaryManager.addDiaryEntry(diaryEntry, diaryId);

    }


    public void PushßtoßtalkcheckSaveButton(){

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
                if(desview.getConcentrationRadioButton().getValue() == null || desview.getSleepRadioButton().getValue() == null || desview.getFitnessRadioButton().getValue() == null)
                {
                    Notification.show("Bitte treffen Sie bei jedem Punkt eine Auswahl!");
                    return;

                }

                addWelfare();

                saveWelfareDiaryEntry(desview.getDataPicker().getValue(), welfareSet);
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
        return;
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
    public void addWelfare()
    {
            welfareSet = new HashSet<>();

            if(desview.getConcentrationRadioButton().getValue() == "gut"){
                welfareSet.add(WelfareFactory.createSymptomByClass( "ConcentrationAbility", Welfare.Strength.WEAK));

            }
            if(desview.getConcentrationRadioButton().getValue() == "mittel") {

            }  welfareSet.add(WelfareFactory.createSymptomByClass( "ConcentrationAbility", Welfare.Strength.MIDDLE));


            if (desview.getConcentrationRadioButton().getValue() == "schlecht"){
                        welfareSet.add(WelfareFactory.createSymptomByClass( "ConcentrationAbility", Welfare.Strength.SEVERE));

                    }

        if(desview.getSleepRadioButton().getValue() == "gut"){
            welfareSet.add(WelfareFactory.createSymptomByClass( "Sleep", Welfare.Strength.WEAK));

        }
        if(desview.getSleepRadioButton().getValue() == "mittel") {

        }  welfareSet.add(WelfareFactory.createSymptomByClass( "Sleep", Welfare.Strength.MIDDLE));


        if (desview.getSleepRadioButton().getValue() == "schlecht"){
            welfareSet.add(WelfareFactory.createSymptomByClass( "Sleep", Welfare.Strength.SEVERE));

        }



        if(desview.getFitnessRadioButton().getValue() == "gut"){
            welfareSet.add(WelfareFactory.createSymptomByClass( "PhysicalCondition", Welfare.Strength.WEAK));

        }
        if(desview.getFitnessRadioButton().getValue() == "mittel") {

        }  welfareSet.add(WelfareFactory.createSymptomByClass( "PhysicalCondition", Welfare.Strength.MIDDLE));


        if (desview.getFitnessRadioButton().getValue() == "schlecht"){
            welfareSet.add(WelfareFactory.createSymptomByClass( "PhysicalCondition", Welfare.Strength.SEVERE));

        }







                }

         public void addRadioButtonListenerPiucture()
         {

             desview.getFitnessRadioButton().addValueChangeListener(valueChangeEvent ->

                     {
                      if(valueChangeEvent.getValue() == "gut")
                      {
                          desview.getFitnessRadiobuttonPicture().setSource(good);
                      }
                         if(valueChangeEvent.getValue() == "mittel")
                         {
                             desview.getFitnessRadiobuttonPicture().setSource(middle);
                         }
                         if(valueChangeEvent.getValue() == "schlecht")
                         {
                             desview.getFitnessRadiobuttonPicture().setSource(bad);
                         }


                     }
                     );

             desview.getSleepRadioButton().addValueChangeListener(valueChangeEvent ->

                     {
                         if(valueChangeEvent.getValue() == "gut")
                         {
                             desview.getSleepRadiobuttonPicture().setSource(good);
                         }
                         if(valueChangeEvent.getValue() == "mittel")
                         {
                             desview.getSleepRadiobuttonPicture().setSource(middle);
                         }
                         if(valueChangeEvent.getValue() == "schlecht")
                         {
                             desview.getSleepRadiobuttonPicture().setSource(bad);
                         }


                     }
             );

             desview.getConcentrationRadioButton().addValueChangeListener(valueChangeEvent ->

                     {
                         if(valueChangeEvent.getValue() == "gut")
                         {
                             desview.getConcentrationRadiobuttonPicture().setSource(good);
                         }
                         if(valueChangeEvent.getValue() == "mittel")
                         {
                             desview.getConcentrationRadiobuttonPicture().setSource(middle);
                         }
                         if(valueChangeEvent.getValue() == "schlecht")
                         {
                             desview.getConcentrationRadiobuttonPicture().setSource(bad);
                         }


                     }
             );





         }





    }












