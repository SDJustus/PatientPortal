package de.tud.view.diaryEvaluation;


import com.vaadin.addon.charts.Chart;
import com.vaadin.annotations.DesignRoot;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;


@DesignRoot(value = "basic_line.html")
public abstract class  ChartView  {


    Chart chart = new Chart();
    VerticalLayout chartContainer = new VerticalLayout();
    protected abstract Component setup() ;








}
