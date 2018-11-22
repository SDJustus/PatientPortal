package de.tud.view;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.model.DiaryEntry;
import de.tud.model.DiaryEntryTableViewAdapter;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;

import java.util.ArrayList;
import java.util.Set;

public class AuswertungView extends Composite implements View {

    public AuswertungView(){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);


        Panel panel = new Panel();
        Grid<DiaryEntryTableViewAdapter> grid = new Grid<>();
        grid.addColumn(DiaryEntryTableViewAdapter::getDate).setCaption("Datum");
        grid.addColumn(DiaryEntryTableViewAdapter::getSymptom).setCaption("Ausprägung der Symptome");


        grid.setHeight(""+0.8*Page.getCurrent().getBrowserWindowHeight());
        grid.setWidth(""+0.8*Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight(""+0.8*e.getHeight());
            grid.setWidth(""+0.8*e.getWidth());

        });

        panel.setContent(grid);
        panel.setSizeFull();

        DiaryManager diaryManager = new DiaryManager();
        Set<DiaryEntry> set = diaryManager.readDiaryEntriesByDiary(new Long(1));

        ArrayList<DiaryEntryTableViewAdapter> diaryEntryTableViewAdapters = new ArrayList<>();
        if(set != null){
            for(DiaryEntry diaryEntry : set){
                for(Symptom s : diaryEntry.getSymptom()){
                    diaryEntryTableViewAdapters.add(new DiaryEntryTableViewAdapter(diaryEntry.getDate().toString(),s ));
                }
            }
            grid.setItems(diaryEntryTableViewAdapters);
        }

        verticalLayout.addComponents(panel);
        setCompositionRoot(verticalLayout);
    }
}
