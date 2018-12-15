package de.tud.view.Homework;

import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import de.tud.view.VitalData.VitalDataUIDesignerUISetup;

public class HomeworkView extends Composite implements View {


    public HomeworkView(){


        setCompositionRoot(new HomeworkSetup());


    }
}
