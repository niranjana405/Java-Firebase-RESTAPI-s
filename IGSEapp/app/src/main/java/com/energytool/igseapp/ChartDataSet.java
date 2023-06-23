package com.energytool.igseapp;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.LineChartData;

public class ChartDataSet extends LineChartData {
    public ChartDataSet(ArrayList<chartentry> yValue, String temp) {

    }

    public ChartDataSet(ChartDataSet lineDataSet) {

    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }

    public ChartDataSet(float yValue, String x) {
        this.yValue = yValue;
    }

    float yValue;
    public ChartDataSet(){

    }

}
