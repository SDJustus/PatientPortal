package de.tud.view.DiaryEvaluation;




import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.declarative.Design;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.manager.DiaryManager;
import de.tud.model.manager.HomeworkManager;
import de.tud.model.symptom.Depression;
import de.tud.model.symptom.Symptom;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import javax.xml.crypto.Data;
import java.time.Instant;
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

        Chart chart = new Chart();
        chart.setWidth("100%");
        chart.setHeight("100%");
        Responsive.makeResponsive(chart);
        Configuration conf = chart.getConfiguration();
        conf.getChart().setType(ChartType.LINE);
        conf.setTitle("Patiententagebuch");


        DataSeries serie1 = new DataSeries("Depression");


        ArrayList<DiaryEntry> diaryEntries = new ArrayList<>(diaryInst.getDiaryEntries());


        Collections.sort(diaryEntries, Comparator.comparing(DiaryEntry::getDate));

        for(DiaryEntry diaryEntry: diaryEntries){
            if(diaryEntry.getSymptom() != null){
                for(Symptom s: diaryEntry.getSymptom()){
                    if(s instanceof Depression){

                        /*
                        serie1.add(new DataSeriesItem(java.sql.Date.valueOf(diaryEntry.getDate().toLocalDate()),
                                s.getStrength().ordinal()+1));
                                */


                        DataSeriesItem dataSeriesItem = new DataSeriesItem();
                        dataSeriesItem.setX(java.sql.Date.valueOf(diaryEntry.getDate().toLocalDate()));
                        dataSeriesItem.setName(diaryEntry.getDate().toString());
                        dataSeriesItem.setY(s.getStrength().ordinal()+1);


                        serie1.add(dataSeriesItem);
                    }

                }

            }

        }





        XAxis xAxis = new XAxis();
        xAxis.setTitle("Datum");
        xAxis.setType(AxisType.DATETIME);


        conf.addxAxis(xAxis);
        YAxis yAxis = new YAxis();
        yAxis.setTitle("Symptomatik");
        yAxis.setType(AxisType.CATEGORY);


        String[] yCat = new String[]{"","gut", "mäßig", "stark"};
        yAxis.setMax(3);
        yAxis.setCategories(yCat);


        conf.addyAxis(yAxis);
        conf.getTooltip().setPointFormatter(
                "function() { " +
                        "var category = this.y; " +
                        "switch (category) " +
                        "{ " +
                        "   case 1: " +
                        "       symptom = 'schwach'; " +
                        "       break; " +
                        "   case 2: " +
                        "       multiplier = 'mäßig'; " +
                        "       break; " +
                        "   case 3: " +
                        "       multiplier = 'stark'; " +
                        "       break; " +
                        "}" +
                        "var tipTxt = this.series.name + ': <b>' + multiplier + '</b><br>'; " +
                        "return tipTxt; " +
                        "}"
        );
        conf.getTooltip().setShared(true);


        /*

        tooltip.setFooterFormat("{function() { if(this.y == 3) { return this.point.name ='stark'; } " +
                " if(this.y == 2) { return this.point.name = 'mäßig'; }" +
                "if(this.y == 1) { return this.point.name = 'schwach'; }}");
                */



        conf.addSeries(serie1);


        PlotOptionsLine serie1Opts = new PlotOptionsLine();
        serie1Opts.setColor(SolidColor.BLUE);
        serie1.setPlotOptions(serie1Opts);


        chart.getConfiguration().setTitle("Zeitlicher Verlauf aller Symptome");



        chartContainer.addComponent(chart);
        return chartContainer;


    }



}
