package de.tud.view.DiaryEvaluation;




import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.manager.DiaryManager;
import de.tud.model.manager.HomeworkManager;
import de.tud.model.symptom.Symptom;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class SymptomChartView extends ChartView {


    /**
     * Returns a layout with a symptomchart.
     */
    @Override
    public Component setup() {

        chart.getConfiguration().getxAxis().setType(AxisType.DATETIME);
        chart.getConfiguration().getyAxis().setType(AxisType.LINEAR);



        DiaryManager diaryManager = DiaryManager.getInstance();
        Diary diaryInst = diaryManager.read().get(0);
        long diaryId = diaryInst.getId();

        Set<DiaryEntry> diary=  DiaryManager.getInstance().readDiaryEntriesByDiary(diaryId);
        List<String> symptomNames = new ArrayList<String>();
        Set<Symptom> symptoms = new HashSet<>();
        Map<LocalDateTime,Symptom> symptomMap = new HashMap<>();
        Set<DataSeries> series = new HashSet<>();
         for(DiaryEntry d:diary)
         {
             for(Symptom s:d.getSymptom())
             {

                     symptomMap.put(d.getDate(), s);
                     if(symptomNames.contains(s.toString()) == false)
                     {
                         symptomNames.add(s.toString());
                     }

             }


         }

         if(symptomMap.isEmpty() == true)
         {
             for(int i =0 ; i<100; i++)
             {
                 System.out.println("Fehler!");

             }

         }

         for(String s:symptomNames)
         {
             DataSeries dataSeries = new DataSeries();
             dataSeries.setName(s);
             series.add(dataSeries);

         }

        Iterator entries = symptomMap.entrySet().iterator();

        for (Map.Entry<LocalDateTime,Symptom> entry : symptomMap.entrySet())
        {

            for(DataSeries s:series)
            {
                if(entry.getValue().toString().equals(s.getName()))
                {

                    Date out = Date.from(entry.getKey().atZone(ZoneId.systemDefault()).toInstant());

                    s.add(new DataSeriesItem(out,entry.getValue().getStrength().ordinal()));

                }

            }

        }


        for(DataSeries s:series)
        {
           chart.getConfiguration().addSeries(s);
        }

        chartContainer.addComponent(chart);
        return chartContainer;


    }

}
