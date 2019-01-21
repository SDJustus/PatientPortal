package de.tud.view.DiaryEvaluation;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.annotations.DesignRoot;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;


@DesignRoot(value = "basic_line.html")
public abstract class  ChartView  {


    Chart chart = new Chart(ChartType.LINE);
    VerticalLayout chartContainer = new VerticalLayout();
    protected abstract Component setup() ;







}
