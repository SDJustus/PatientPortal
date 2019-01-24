package de.tud.view.DiaryEvaluation;




import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.manager.DiaryManager;
import de.tud.model.manager.HomeworkManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.welfare.ConcentrationAbility;
import de.tud.model.welfare.PhysicalCondition;
import de.tud.model.welfare.Sleep;
import de.tud.model.welfare.Welfare;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.locks.Condition;

public class WelfareChartView extends ChartView {


    /**
     * Returns a layout with a symptomchart.
     */
    @Override
    public Component setup() {

        chart.getConfiguration().getxAxis().setType(AxisType.DATETIME);
        chart.getConfiguration().getyAxis().setType(AxisType.LINEAR);



        DiaryManager diaryManager = DiaryManager.getInstance();
        Diary diaryInst = diaryManager.read().get(0);
        long diaryId = 1;

        Set<DiaryEntry> diary=  DiaryManager.getInstance().readDiaryEntriesByDiary(diaryId);

        Map<LocalDateTime,Welfare> sleepMap = new HashMap<>();
        Map<LocalDateTime,Welfare> conditionMap = new HashMap<>();
        Map<LocalDateTime,Welfare> concentrationMap = new HashMap<>();
        Set<DataSeries> series = new HashSet<>();




        for(DiaryEntry d:diary)
        {




            for(Welfare w:d.getWelfare())
            {



                if(w instanceof Sleep == true)
                {
                   sleepMap.put(d.getDate(), w);
                }
                if(w instanceof PhysicalCondition == true)
                {
                    conditionMap.put(d.getDate(), w);
                }
                if(w instanceof ConcentrationAbility == true)
                {
                    concentrationMap.put(d.getDate(), w);
                }




            }


        }



            DataSeries sleep = new DataSeries(); sleep.setName("Schlaf");
            PlotOptionsLine options = new PlotOptionsLine();
            options.setStep(StepType.RIGHT);
            sleep.setPlotOptions(options);


            DataSeries concentration = new DataSeries(); concentration.setName("Konzentration");
            options = new PlotOptionsLine();
             options.setStep(StepType.CENTER);
            concentration.setPlotOptions(options);

            DataSeries condition = new DataSeries(); condition.setName("Kondition");
            options = new PlotOptionsLine();
            options.setStep(StepType.LEFT);
             condition.setPlotOptions(options);

            series.add(sleep);
            series.add(concentration);
            series.add(condition);





        Iterator sleepEntries = sleepMap.entrySet().iterator();
        Iterator concentrationEntries = concentrationMap.entrySet().iterator();
        Iterator conditionEntries =    conditionMap.entrySet().iterator();


        for (Map.Entry<LocalDateTime,Welfare> entry : sleepMap.entrySet())
        {




                    Date out = Date.from(entry.getKey().atZone(ZoneId.systemDefault()).toInstant());


                    sleep.add(new DataSeriesItem(out,entry.getValue().getStrength().ordinal()));




        }
        for (Map.Entry<LocalDateTime,Welfare> entry : conditionMap.entrySet())
        {


                    Date out = Date.from(entry.getKey().atZone(ZoneId.systemDefault()).toInstant());
                    condition.add(new DataSeriesItem(out,entry.getValue().getStrength().ordinal()));




        }
        for (Map.Entry<LocalDateTime,Welfare> entry : concentrationMap.entrySet())
        {




                    Date out = Date.from(entry.getKey().atZone(ZoneId.systemDefault()).toInstant());
                    concentration.add(new DataSeriesItem(out,entry.getValue().getStrength().ordinal()));


        }



            chart.getConfiguration().addSeries(sleep);
            chart.getConfiguration().addSeries(condition);
            chart.getConfiguration().addSeries(concentration);

        chart.getConfiguration().setTitle("Zeitlicher Verlauf des Wohlbefindens");
        chartContainer.addComponent(chart);
        return chartContainer;




    }

}
