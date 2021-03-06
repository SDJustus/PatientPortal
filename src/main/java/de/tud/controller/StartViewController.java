package de.tud.controller;

import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.vaadin.data.provider.Query;
import com.vaadin.ui.CheckBox;
import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import de.tud.view.start.StartView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StartViewController {


    /**
     * Holds the Start View.
     */
    private StartView startView;


    /**
     * Executes the initGrid() method to setup the tables on the start page.
     */

    public StartViewController(StartView startView){
        this.startView = startView;
        initGrids();
    }

    /**
     * Initialize exercise/homework overview tables.
     */
    private void initGrids(){
        List<Homework> homeworkList = null;
        try {
           homeworkList = HomeworkManager.getInstance().read();
        }catch (NullPointerException e){
            e.getMessage();
        }
        LocalDate today = LocalDate.now();
        ArrayList<Homework> todayGridList = new ArrayList<>();
        ArrayList<Homework> fulfilledGridList = new ArrayList<>();

        if(homeworkList != null){
            for(Homework s : homeworkList){
                if(s.getDate() == null){
                    continue;
                }
                if(s.getDate().toLocalDate().equals(today) && s.isStatus() == false){
                    todayGridList.add(s);

                }else if(s.getDate().toLocalDate().equals(today) && s.isStatus() == true){
                    fulfilledGridList.add(s);
                }
            }
            startView.getTodayGrid().setItems(todayGridList);
            notificationGenerator(todayGridList);
            startView.getTodayGrid().setHeightByRows(startView.getTodayGrid().getDataProvider().size(new Query<>())+1);

            startView.getFulfilledGrid().setItems(fulfilledGridList);
            startView.getFulfilledGrid().setHeightByRows(startView.getFulfilledGrid().getDataProvider().size(new Query<>())+1);

        }

    }
    /**
     * Generator method for notifications in the upper right corner of the UI.
     */
    private void notificationGenerator(ArrayList<Homework> todayList){
        PatientPortalController.getNotifications().clearNotifications();
        if(todayList != null){
            for (Homework homework: todayList){
                PatientPortalController.getNotifications().addNotification(new DefaultNotification("Heute erledigen:", homework.getDescription()));
            }
        }


    }

    /**
     * Method for shifting exercise/homework elements if fulfilled into another table.
     */

    public void checkBoxListener(Long id, CheckBox checkBox){
        HomeworkManager.getInstance().setHomeworkStatus(id, !checkBox.getValue());
        initGrids();
    }


}
