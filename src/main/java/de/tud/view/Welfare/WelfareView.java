package de.tud.view.Welfare;

import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import de.tud.view.VitalData.VitalDataUIDesignerUISetup;

public class WelfareView extends Composite implements View {


    public WelfareView(){


        setCompositionRoot(new WelfareUISetup());


    }
}
