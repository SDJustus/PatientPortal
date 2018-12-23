package de.tud.controller;

import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.vaadin.data.provider.Query;
import com.vaadin.ui.CheckBox;
import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import de.tud.view.Start.StartView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StartViewController {
    private StartView startView;
    HomeworkManager homeworkManager = new HomeworkManager();



    public StartViewController(StartView startView){
        this.startView = startView;
        initGrids();
    }
    private void initGrids(){
        List<Homework> homeworkList = homeworkManager.read();
        LocalDate today = LocalDate.now();
        ArrayList<Homework> todayGridList = new ArrayList<>();
        ArrayList<Homework> fulfilledGridList = new ArrayList<>();
        ArrayList<Homework> nextDaysGridList = new ArrayList<>();

        if(homeworkList != null){
            for(Homework s : homeworkList){
                if(s.getDate().toLocalDate().equals(today) && s.isStatus() == false){
                    todayGridList.add(s);
                }else if(s.getDate().toLocalDate().equals(today) && s.isStatus() == true){
                    fulfilledGridList.add(s);
                }else{
                    nextDaysGridList.add(s);
                }
            }
            startView.getTodayGrid().setItems(todayGridList);
            notificationGenerator(todayGridList);
            startView.getTodayGrid().setHeightByRows(startView.getTodayGrid().getDataProvider().size(new Query<>())+1);

            startView.getFulfilledGrid().setItems(fulfilledGridList);
            startView.getFulfilledGrid().setHeightByRows(startView.getFulfilledGrid().getDataProvider().size(new Query<>())+1);

            startView.getNextDaysGrid().setItems(nextDaysGridList);
            startView.getNextDaysGrid().setHeightByRows(startView.getNextDaysGrid().getDataProvider().size(new Query<>())+1);
        }

    }
    private void notificationGenerator(ArrayList<Homework> todayList){
        PatientPortalController.getNotifications().clearNotifications();
        if(todayList != null){
            for (Homework homework: todayList){
                PatientPortalController.getNotifications().addNotification(new DefaultNotification("Heute erledigen:", homework.getDescription()));
            }
        }


    }


    public void checkBoxListener(Long id, CheckBox checkBox){
        homeworkManager.setHomeworkStatus(id, !checkBox.getValue());
        initGrids();
    }


}
