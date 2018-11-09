package de.tud.view;

import com.vaadin.ui.*;
import com.vaadin.navigator.View;




public class DefaultView extends Composite implements View {
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    com.vaadin.ui.Label label = new com.vaadin.ui.Label("Das ist die Startseite des Patientenportals.");



    public DefaultView(){
        setCompositionRoot(horizontalLayout);
        horizontalLayout.addComponents(label);

    }

}