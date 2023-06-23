package com.energytool.igseapp;

public class dModel {

    String email_id;
    String password_cu;
    String address_cu;
    String property_type;
    int bedroom_no;
    String energy_code;

//default constructor

    public dModel(String email_id, String password_cu, String address_cu, String property_type, int bedroom_no, String energy_code) {
        this.email_id = email_id;
        this.password_cu = password_cu;
        this.address_cu = address_cu;
        this.property_type = property_type;
        this.bedroom_no = bedroom_no;
        this.energy_code = energy_code;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword_cu() {
        return password_cu;
    }

    public void setPassword_cu(String password_cu) {
        this.password_cu = password_cu;
    }

    public String getAddress_cu() {
        return address_cu;
    }

    public void setAddress_cu(String address_cu) {
        this.address_cu = address_cu;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public int getBedroom_no() {
        return bedroom_no;
    }

    public void setBedroom_no(int bedroom_no) {
        this.bedroom_no = bedroom_no;
    }

    public String getEnergy_code() {
        System.out.println("{{");

        return energy_code;
    }

    public void setEnergy_code(String energy_code) {
        this.energy_code = energy_code;
    }

    public dModel() {

    }
}