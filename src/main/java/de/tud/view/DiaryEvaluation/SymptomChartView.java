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
import de.tud.model.symptom.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

        Set<DataSeries> series = new HashSet<>();

        Chart chart = new Chart();
        chart.setWidth("100%");
        chart.setHeight("100%");
        Responsive.makeResponsive(chart);
        Configuration conf = chart.getConfiguration();
        conf.getChart().setType(ChartType.LINE);
        conf.setTitle("Patiententagebuch");


        DataSeries seriesDepression = new DataSeries("Depression");
        DataSeries seriesAche = new DataSeries("Schmerzen");
        DataSeries seriesBladderDisorder = new DataSeries("Blasenstörung");
        DataSeries seriesBowelDisorder = new DataSeries("Darmstörung");
        DataSeries seriesCognitiveDisorder = new DataSeries("Kognitive Störung");
        DataSeries seriesFatigue = new DataSeries("Müdigkeit");
        DataSeries seriesGaitDisorder = new DataSeries("Gehstörung");
        DataSeries seriesLeftArmSpasticity = new DataSeries("Spastik im linken Arm");
        DataSeries seriesLeftLegSpasticity = new DataSeries("Spastik im linken Bein");
        DataSeries seriesRightArmSpasticity = new DataSeries("Spastik im rechten Arm");
        DataSeries seriesRightLegSpasticity = new DataSeries("Spastik im rechten Bein");


        ArrayList<DiaryEntry> diaryEntries = new ArrayList<>(diaryInst.getDiaryEntries());

        Set<DataSeries> dataSeries = new HashSet<>();


        Collections.sort(diaryEntries, Comparator.comparing(DiaryEntry::getDate));


        for(DiaryEntry diaryEntry: diaryEntries){
            if(diaryEntry.getSymptom() != null){
                for(Symptom s: diaryEntry.getSymptom()){

                    if(s instanceof RightLegSpasticity){
                        seriesRightLegSpasticity.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        dataSeries.add(seriesRightLegSpasticity);
                    }

                    if(s instanceof LeftLegSpasticity){
                        seriesLeftLegSpasticity.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        dataSeries.add(seriesLeftLegSpasticity);
                    }

                    if(s instanceof  LeftArmSpasticity){
                        seriesLeftArmSpasticity.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        dataSeries.add(seriesLeftArmSpasticity);
                    }

                    if(s instanceof  GaitDisorder){
                        seriesGaitDisorder.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        dataSeries.add(seriesGaitDisorder);
                    }


                    if(s instanceof Fatigue){
                        seriesFatigue.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        dataSeries.add(seriesFatigue);
                    }


                    if(s instanceof CognitiveDisorder){
                        seriesCognitiveDisorder.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        series.add(seriesCognitiveDisorder);
                    }

                    if(s instanceof BowelDisorder){
                        seriesBowelDisorder.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        series.add(seriesBowelDisorder);
                    }

                    if(s instanceof BladderDisorder){
                        seriesBladderDisorder.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        series.add(seriesBladderDisorder);
                    }

                    if(s instanceof Ache){
                        seriesAche.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        series.add(seriesAche);
                    }

                    if(s instanceof RightArmSpasticity){
                        seriesRightArmSpasticity.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        series.add(seriesRightArmSpasticity);
                    }

                    if(s instanceof Depression){
                        seriesDepression.add(createDataSeriesItem(diaryEntry.getDate(), s));
                        series.add(seriesDepression);
                    }

                }

            }

        }

        dataSeries.add(seriesDepression);
        dataSeries.add(seriesAche);
        dataSeries.add(seriesFatigue);
        dataSeries.add(seriesBladderDisorder);
        dataSeries.add(seriesBowelDisorder);
        dataSeries.add(seriesCognitiveDisorder);
        dataSeries.add(seriesGaitDisorder);
        dataSeries.add(seriesLeftArmSpasticity);
        dataSeries.add(seriesRightArmSpasticity);
        dataSeries.add(seriesRightLegSpasticity);
        dataSeries.add(seriesLeftLegSpasticity);

        boolean count = true;
        for(DataSeries dataSeries1: dataSeries){
            if (dataSeries1.getData() != null){
                dataSeries1.setVisible(false);
                if(count == true && dataSeries1.getData().size() >= 4){
                    dataSeries1.setVisible(true);
                    conf.addSeries(dataSeries1);
                    count = false;
                    continue;
                }
                conf.addSeries(dataSeries1);
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
                        "       multiplier = 'schwach'; " +
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


        PlotOptionsLine serie1Opts = new PlotOptionsLine();
        serie1Opts.setColor(SolidColor.BLUE);
        seriesDepression.setPlotOptions(serie1Opts);

        chart.getConfiguration().setTitle("Zeitlicher Verlauf aller Symptome");


        chartContainer.addComponent(chart);
        return chartContainer;

    }
    private DataSeriesItem createDataSeriesItem(LocalDateTime localDateTime, Symptom symptom){
        DataSeriesItem dataSeriesItem = new DataSeriesItem();
        dataSeriesItem.setX(java.sql.Date.valueOf(localDateTime.toLocalDate()));
        DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("dd.MM.YYYY hh:mm");
        dataSeriesItem.setName(localDateTime.format(dateTimeFormatter).toString());
        dataSeriesItem.setY(symptom.getStrength().ordinal()+1);
        return dataSeriesItem;
    }



}
