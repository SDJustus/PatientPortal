package de.tud.view;

import com.vaadin.navigator.View;
import de.tud.controller.MedicationScheduleController;
import de.tud.view.MedicationSchedule;
import com.vaadin.ui.Label;
import com.vaadin.ui.Grid;


public class MedicationScheduleSetup extends MedicationSchedule implements View {

    MedicationScheduleController controller;

    MedicationScheduleSetup(){
        controller = new MedicationScheduleController(this);
        controller.loadMedicationSchedule();
    }

    public Label getLabel(){ return headline;}

    public void setLabel(Label label){headline = label;}

    public Grid getGrid(){ return medicationSchedule;}

    public void setGrid(Grid grid){medicationSchedule = grid;}
}
