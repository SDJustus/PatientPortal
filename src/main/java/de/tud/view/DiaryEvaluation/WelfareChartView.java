package de.tud.view.DiaryEvaluation;




import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.manager.DiaryManager;
import de.tud.model.welfare.ConcentrationAbility;
import de.tud.model.welfare.PhysicalCondition;
import de.tud.model.welfare.Sleep;
import de.tud.model.welfare.Welfare;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


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
        long diaryId = diaryInst.getId();

        Set<DataSeries> series = new HashSet<>();

        Chart chart = new Chart();
        chart.setWidth("100%");
        chart.setHeight("100%");
        Responsive.makeResponsive(chart);
        Configuration conf = chart.getConfiguration();
        conf.getChart().setType(ChartType.LINE);
        conf.setTitle("Patiententagebuch");


        DataSeries seriesPhysicalCondition = new DataSeries("Fitness");
        DataSeries seriesSleep = new DataSeries("Schlaf");
        DataSeries seriesConcentrationAbility = new DataSeries("Konzentration");



        ArrayList<DiaryEntry> diaryEntries = new ArrayList<>(diaryInst.getDiaryEntries());

        Set<DataSeries> dataSeries = new HashSet<>();


        Collections.sort(diaryEntries, Comparator.comparing(DiaryEntry::getDate));


        for(DiaryEntry diaryEntry: diaryEntries){
            if(diaryEntry.getSymptom() != null){
                for(Welfare welfare: diaryEntry.getWelfare()){

                    if(welfare instanceof PhysicalCondition){
                        seriesPhysicalCondition.add(createDataSeriesItem(diaryEntry.getDate(), welfare));
                        dataSeries.add(seriesPhysicalCondition);
                    }

                    if(welfare instanceof Sleep){
                        seriesSleep.add(createDataSeriesItem(diaryEntry.getDate(), welfare));
                        dataSeries.add(seriesSleep);
                    }

                    if(welfare instanceof ConcentrationAbility){
                        seriesConcentrationAbility.add(createDataSeriesItem(diaryEntry.getDate(), welfare));
                        dataSeries.add(seriesConcentrationAbility);
                    }


                }

            }

        }

        dataSeries.add(seriesConcentrationAbility);
        dataSeries.add(seriesPhysicalCondition);
        dataSeries.add(seriesSleep);


        boolean count = true;
        for(DataSeries dataSeries1: dataSeries){
            if (dataSeries1.getData().size() != 0){
                dataSeries1.setVisible(false);
                if(count == true && dataSeries1.getData().size() >= 2){
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
        seriesSleep.setPlotOptions(serie1Opts);

        chart.getConfiguration().setTitle("Zeitlicher Verlauf des Wohlbefindens");
        chartContainer.addComponent(chart);
        return chartContainer;

    }
    private DataSeriesItem createDataSeriesItem(LocalDateTime localDateTime, Welfare welfare){
        DataSeriesItem dataSeriesItem = new DataSeriesItem();
        dataSeriesItem.setX(java.sql.Date.valueOf(localDateTime.toLocalDate()));
        DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("dd.MM.YYYY hh:mm");
        dataSeriesItem.setName(localDateTime.format(dateTimeFormatter).toString());
        dataSeriesItem.setY(welfare.getStrength().ordinal()+1);

        return dataSeriesItem;
    }


}
