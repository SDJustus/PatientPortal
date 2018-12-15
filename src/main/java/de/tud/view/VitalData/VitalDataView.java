package de.tud.view.VitalData;

import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import de.tud.view.VitalData.VitalDataUIDesignerUISetup;

public class VitalDataView extends Composite implements View {


    public VitalDataView(){






        setCompositionRoot(new VitalDataUIDesignerUISetup());


    }
}
