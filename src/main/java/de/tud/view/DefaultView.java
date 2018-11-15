package de.tud.view;

import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import de.tud.model.DiaryEntryTableViewAdapter;
import de.tud.model.symptom.Depression;


public class DefaultView extends Composite implements View {
    VerticalLayout formLayout = new VerticalLayout();
    //com.vaadin.ui.Label label = new com.vaadin.ui.Label("Das ist die Startseite des Patientenportals.");


    public DefaultView(){
        UI.getCurrent().getPage().getStyles().add("#smileybild:hover{transform: scale(1.2);}"+
                "#smileybild:{transition: transform .2s;}+" +
                ".v-panel{padding-bottom: 80px;}");

        formLayout.setSpacing(true);
        Panel panel = new Panel();
        panel.setSizeFull();

        panel.setHeight(""+0.9*Page.getCurrent().getBrowserWindowHeight());
        panel.setWidth(""+-200+Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            panel.setHeight(""+0.9*e.getHeight());
            panel.setWidth(""+-200+e.getWidth());

        });

        formLayout.addComponents(new SymptomSelectionViewFactory("Depression").getSymptomSelectionView());
        formLayout.addComponents(new SymptomSelectionViewFactory("Müdigkeit").getSymptomSelectionView());
        formLayout.addComponents(new SymptomSelectionViewFactory("Spastik").getSymptomSelectionView());
        formLayout.addComponents(new SymptomSelectionViewFactory("Schmerzen").getSymptomSelectionView());

        formLayout.setSizeUndefined();
        formLayout.setMargin(true);
        panel.setContent(formLayout);


        //formLayout.setExpandRatio(formLayout.getComponent(0), 0.1f);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);

        Button save = new Button("Speichern");
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });


        Grid<DiaryEntryTableViewAdapter> grid = new Grid<>();
        grid.addColumn(DiaryEntryTableViewAdapter::getSymptom).setCaption("Ausprägung der Symptome");


        horizontalLayout.addComponents(panel, new VerticalLayout(save,grid));
        setCompositionRoot(horizontalLayout);

        //setCompositionRoot(label);
    }

}