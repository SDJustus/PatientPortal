package de.tud.view;

import com.vaadin.ui.*;
import java.util.ArrayList;
import com.vaadin.navigator.View;
import java.time.LocalDateTime;
import java.util.*;
import com.vaadin.server.*;
import de.tud.model.DiaryEntryTableViewAdapter;


public class DefaultView extends Composite implements View {
    VerticalLayout formLayout = new VerticalLayout();
    com.vaadin.ui.Label label = new com.vaadin.ui.Label("Das ist die Startseite des Patientenportals.");
    Button save = new Button("Speichern");

    private List<DiaryEntryTableViewAdapter> tagebuch = new ArrayList<>();

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

        ArrayList<String> mySymptoms = new ArrayList<String>();
        mySymptoms.add("Depression");
        mySymptoms.add("Fatigue");
        mySymptoms.add("Spasticity");

        //SymptomSelectionView Objekte erstellen
        ArrayList<SymptomSelectionViewFactory> symptomSelectionViewFactories = new ArrayList<>();
        for(String s : mySymptoms){
            symptomSelectionViewFactories.add(new SymptomSelectionViewFactory(s));
        }
        //Boxen zum Format Layout hinzufügen
        for(SymptomSelectionViewFactory symptomSelectionViewFactory:symptomSelectionViewFactories){
            formLayout.addComponent(symptomSelectionViewFactory.getSymptomSelectionView());
        }



        formLayout.addComponents(new SymptomSelectionViewFactory("Depression").getSymptomSelectionView());
        formLayout.addComponents(new SymptomSelectionViewFactory("Fatigue").getSymptomSelectionView());
        formLayout.addComponents(new SymptomSelectionViewFactory("Spasticity").getSymptomSelectionView());


        formLayout.setSizeUndefined();
        formLayout.setMargin(true);
        panel.setContent(formLayout);


        //formLayout.setExpandRatio(formLayout.getComponent(0), 0.1f);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);

        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<DiaryEntryTableViewAdapter> diaryEntryTableViewAdapter = new ArrayList<>();

        Grid<DiaryEntryTableViewAdapter> grid = new Grid<>();
        grid.addColumn(DiaryEntryTableViewAdapter::getDate).setCaption("Datum");
        grid.addColumn(DiaryEntryTableViewAdapter::getSymptom).setCaption("Symptom");


        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                for(SymptomSelectionViewFactory s:symptomSelectionViewFactories){
                    if(s.getSelection() == null){
                        break;
                    }
                    System.out.println(s.getSymptomName() +" "+ s.getSelection());
                    hashMap.put(s.getSymptomName(), s.getSelection().toString());

                    diaryEntryTableViewAdapter.add(new DiaryEntryTableViewAdapter(LocalDateTime.now().toString(), s.getSymptomArt()));

                }
                if(hashMap.isEmpty()){
                    Notification.show("Es muss mindestens ein Symptom ausgewählt werden!");
                    return;
                }
                tagebuch = diaryEntryTableViewAdapter;
                grid.setItems(tagebuch);
            }
        });


        horizontalLayout.addComponents(panel, new VerticalLayout(save,grid));
        setCompositionRoot(horizontalLayout);

        //setCompositionRoot(label);
    }

    /*
    public void saveButton(ArrayList<SymptomSelectionViewFactory> symptomSelectionViewFactories){
        for (SymptomSelectionViewFactory symptomSelectionViewFactory : symptomSelectionViewFactories) {
            if (symptomSelectionViewFactory.getSelection() == null) {
                save.setEnabled(false);
                return;
            }
        }
    }
    */


}