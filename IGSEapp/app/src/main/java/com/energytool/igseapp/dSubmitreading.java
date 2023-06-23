package com.energytool.igseapp;

public class dSubmitreading {
    String customerid;
    String dayreadings;

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getDayreadings() {
        return dayreadings;
    }

    public void setDayreadings(String dayreadings) {
        this.dayreadings = dayreadings;
    }

    public String getNightreadings() {
        return nightreadings;
    }

    public void setNightreadings(String nightreadings) {

        this.nightreadings = nightreadings;
    }

    public String getGasreadings() {
        return gasreadings;
    }

    public void setGasreadings(String gasreadings) {
        this.gasreadings = gasreadings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    String nightreadings;

    String gasreadings;
    String date;

    public dSubmitreading(String customerid, String dayreadings, String nightreadings, String gasreadings, String date) {
        this.customerid = customerid;
        this.dayreadings = dayreadings;
        this.nightreadings = nightreadings;
        this.gasreadings = gasreadings;
        this.date = date;

    }


    public dSubmitreading(){


    }
}
