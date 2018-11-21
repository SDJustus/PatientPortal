package de.tud.view;

import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class TagebuchViewNew {
    HorizontalLayout horizontalLayout; //Seitenstruktur
    VerticalLayout verticalLayout;  //Vertical Layout wird in horizontal Layout gepackt
    DateTimeField dateTimeField; //wird in Vertical Layout gepackt
    GridLayout gridLayout; // wird auch in Vertical Layout gepackt, evtl. Panel später einfügen zum Scrollen

}
