package de.tud.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import java.util.ArrayList;
import com.vaadin.navigator.View;
import java.time.LocalDateTime;
import java.util.*;
import com.vaadin.server.*;
import de.tud.controller.DiaryViewController;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;


public class DiaryView extends Composite implements View {
    private VerticalLayout verticalLayoutMain = new VerticalLayout();        //Seitenstruktur
    private HorizontalLayout menuBar = new HorizontalLayout();
    private VerticalLayout verticalLayout = new VerticalLayout(); //Vertical Layout wird in horizontal Layout gepackt
    private DateTimeField dateTimeField = new DateTimeField(); //wird in Vertical Layout gepackt
    private Label label = new Label("Neuer Tagebucheintrag");
    private Button save = new Button("Speichern");
    private Button newDiaryEntry = new Button("Eintrag verwerfen", VaadinIcons.TRASH);
    private Panel panel = new Panel();
    private DiaryViewController diaryViewController;


    public DiaryView(){
        //Verbindung zu DiaryViewController
       diaryViewController = new DiaryViewController(this);

       //CSS
        UI.getCurrent().getPage().getStyles().add("#smileybild:hover{transform: scale(1.2);}"+
                "#smileybild:{transition: transform .2s;}+" +
                ".v-panel{padding-bottom: 80px;}"+
                "#header-label{font-weight: bold; font-size:40px;}");

        //Label Id für CSS
        label.setId("header-label");

        //save Button ausschalten standardmäßig
        save.setEnabled(false);

        addDateTimeFieldChangeListener();
        menuBar.addComponents(dateTimeField,save, newDiaryEntry);

        verticalLayout.setSizeUndefined();
        verticalLayout.setSpacing(true);
        panel.setContent(verticalLayout);

        panel.setHeight(""+0.8*Page.getCurrent().getBrowserWindowHeight());
        panel.setWidth(""+-200+Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            panel.setHeight(""+0.8*e.getHeight());
            panel.setWidth(""+-200+e.getWidth());
        });

        addSaveButtonListener();
        addNewDiaryEntryButtonListener();


        verticalLayoutMain.addComponents(label,menuBar,panel);
        setCompositionRoot(verticalLayoutMain);
    }

    private void addDateTimeFieldChangeListener(){
        diaryViewController.addDateTimeFieldChangeListener();
    }
    private void addSaveButtonListener(){
        diaryViewController.addSaveButtonListener();
    }
    private void addNewDiaryEntryButtonListener(){
       diaryViewController.addNewDiaryEntryButtonListener();
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