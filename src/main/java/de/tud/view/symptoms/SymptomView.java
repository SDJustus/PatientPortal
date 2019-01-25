package de.tud.view.symptoms;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import de.tud.controller.SymptomViewController;


public class SymptomView extends Composite implements View {
    private VerticalLayout verticalLayoutMain = new VerticalLayout();        //Seitenstruktur
    private HorizontalLayout menuBar = new HorizontalLayout();
    private VerticalLayout verticalLayout = new VerticalLayout(); //Vertical Layout wird in horizontal Layout gepackt
    private DateTimeField dateTimeField = new DateTimeField(); //wird in Vertical Layout gepackt
    private Label label = new Label("Neuer Tagebucheintrag");
    private Button save = new Button("Speichern");
    private Button newDiaryEntry = new Button("Alles Zurücksetzen");
    private Panel panel = new Panel();
    private SymptomViewController symptomViewController;
    int height = 260;
    int width = 250;

    /**
     * setup the symptom diary entry view
     */

    public SymptomView(){
        //Verbindung zu SymptomViewController
       symptomViewController = new SymptomViewController(this);
        UI.getCurrent().getPage().getStyles().add(".v-panel{padding-bottom: 80px;}"+
                "#greyscale{filter: grayscale(100%);" +
                "-webkit-filter: grayscale(100%);" +
                "-moz-filter: grayscale(100%);" +
                "-ms-filter: grayscale(100%);" +
                "-o-filter:grayscale(100%);" +
                "filter: url(desaturate.svg#greyscale);" +
                "filter: gray;" +
                "-webkit-filter: grayscale(1);}");

        //Label Id für CSS
        label.addStyleName("header-label");
        label.addStyleName(MaterialTheme.LABEL_H1);
        label.addStyleName(MaterialTheme.CARD_0_5);


        //Eingaben vermeiden Date-TimeField
        dateTimeField.setTextFieldEnabled(false);

        //save Button ausschalten standardmäßig
        save.setEnabled(false);
        save.setIcon(new ClassResource("/saveicon.png"));


        addDateTimeFieldChangeListener();
        menuBar.addComponents(dateTimeField,save, newDiaryEntry);

        verticalLayout.setSizeUndefined();
        verticalLayout.setSpacing(true);
        panel.setContent(verticalLayout);

        panel.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        panel.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            panel.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            panel.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        addSaveButtonListener();
        addNewDiaryEntryButtonListener();


        verticalLayoutMain.addComponents(label,menuBar,panel);
        setCompositionRoot(verticalLayoutMain);
    }


    private void addDateTimeFieldChangeListener(){
        symptomViewController.addDateTimeFieldChangeListener();
    }
    private void addSaveButtonListener(){
        symptomViewController.addSaveButtonListener();
    }
    private void addNewDiaryEntryButtonListener(){
       symptomViewController.addNewDiaryEntryButtonListener();
    }
    public VerticalLayout getVerticalLayout() {
        return verticalLayout;
    }
    public DateTimeField getDateTimeField() {
        return dateTimeField;
    }
    public Button getSave() {
        return save;
    }
    public Button getNewDiaryEntry() {
        return newDiaryEntry;
    }

}