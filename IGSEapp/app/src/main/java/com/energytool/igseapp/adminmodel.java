package com.energytool.igseapp;

public class adminmodel {
    public String getAdmin_email1() {
        return admin_email1;
    }

    public void setAdmin_email1(String admin_email1) {
        this.admin_email1 = admin_email1;
    }

    public String getAdmin_password1() {
        return admin_password1;
    }

    public void setAdmin_password1(String admin_password1) {
        this.admin_password1 = admin_password1;
    }

    String admin_email1;
    String admin_password1;

    public adminmodel(String admin_email1, String admin_password1) {
        this.admin_email1 = admin_email1;
        this.admin_password1 = admin_password1;
    }

public adminmodel(){

}
}
