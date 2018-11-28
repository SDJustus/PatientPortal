package de.tud.view;


import com.vaadin.navigator.View;
import com.vaadin.ui.Label;


public class StartView  extends com.vaadin.ui.Composite implements View {

    public StartView(){

        Label label = new Label("Das ist die Startseite des Patientenportals");
        setCompositionRoot(label);

    }
}
