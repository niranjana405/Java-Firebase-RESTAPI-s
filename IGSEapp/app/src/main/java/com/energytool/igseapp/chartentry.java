package com.energytool.igseapp;

public class chartentry {
    float x;

    public chartentry(float x, float gasreadings) {
        this.x = x;
        this.gasreadings = gasreadings;
    }

    float gasreadings;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getGasreadings() {
        return gasreadings;
    }

    public void setGasreadings(float gasreadings) {
        this.gasreadings = gasreadings;
    }




    public chartentry(){

    }
}
