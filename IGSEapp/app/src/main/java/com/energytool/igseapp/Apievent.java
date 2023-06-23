package com.energytool.igseapp;

public class Apievent {



    String property_type;

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getAverage_electricity_gas_cost_per_day() {
        return average_electricity_gas_cost_per_day;
    }

    public void setAverage_electricity_gas_cost_per_day(int average_electricity_gas_cost_per_day) {
        this.average_electricity_gas_cost_per_day = average_electricity_gas_cost_per_day;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    int bedroom;
    int average_electricity_gas_cost_per_day;
    String unit;


}
