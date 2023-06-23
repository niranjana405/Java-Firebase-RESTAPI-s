package com.energytool.igseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class admindashboard extends AppCompatActivity {
Button Signout,MeterReading,changeMeterReading,EnergyStatistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        Signout=findViewById(R.id.admin_signout);
        MeterReading=findViewById(R.id.adminmeter_reading);
        EnergyStatistics=findViewById(R.id.energy_stat);
        changeMeterReading=findViewById(R.id.changemeter1_reading);
        EnergyStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(admindashboard.this, ChartVisualise.class);

                startActivity(intent);
            }
        });
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(admindashboard.this, MainActivity.class);

                startActivity(intent);
            }
        });
        MeterReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(admindashboard.this, viewmeterreading.class);
                startActivity(intent);
            }
        });
        changeMeterReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admindashboard.this, energypricesetting.class);
                startActivity(intent);
            }
        });
    }

}