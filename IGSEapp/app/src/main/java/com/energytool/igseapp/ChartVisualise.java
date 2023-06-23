package com.energytool.igseapp;
import android.content.Context;
import android.os.DropBoxManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.lang.Float;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.graphics.*;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.LineChartView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.widget.Toast.LENGTH_SHORT;
//import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import lecho.lib.hellocharts.view.LineChartView;

public class ChartVisualise extends AppCompatActivity {
LineChartView linechart;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
     ArrayList<chartentry> yValue;
     LineChartView line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_visualise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linechart = findViewById(R.id.line_chart);
        yValue = new ArrayList<>();
    }
    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference uidRef = FirebaseDatabase.getInstance().getReference("MeterReadings");
uidRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {


        ArrayList<chartentry> yData = new ArrayList<>();
        ChartDataSet lineDataSet = new ChartDataSet(yValue,"Temp");

        for (DataSnapshot snap : snapshot.getChildren()){
            Long tsLong = System.currentTimeMillis()/1000;
            float SensorValue = (float)snap.child("gasreadings").getValue();
            yValue.add(new chartentry(tsLong,SensorValue));
        }

        ChartDataSet data = new ChartDataSet (lineDataSet);
        linechart.setLineChartData(data);
        linechart.notify();
        linechart.invalidate();


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(ChartVisualise.this, "Fail to load post", Toast.LENGTH_SHORT).show();

    }
});





        }


}

