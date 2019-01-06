package de.tud.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;

public class MedicationScheduleView extends Composite implements View {

    public MedicationScheduleView(){
        setCompositionRoot(new MedicationScheduleSetup());
    }
}
