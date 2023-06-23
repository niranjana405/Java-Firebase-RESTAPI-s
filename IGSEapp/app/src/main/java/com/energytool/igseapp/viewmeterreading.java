package com.energytool.igseapp;

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

import java.util.ArrayList;
import java.util.List;

public class viewmeterreading extends AppCompatActivity {
    RecyclerView recyclerView;
     FirebaseDatabase firebaseDatabase;
     DatabaseReference databaseRef;
     recyclerview_reading adapter;
     ArrayList<dSubmitreading> arrayList;
     Button Home,HomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmeterreading);
        Home=findViewById(R.id.homepage);
       recyclerView=findViewById(R.id.readinglist);
       HomePage=findViewById(R.id.homepage);
        firebaseDatabase=FirebaseDatabase.getInstance();

        databaseRef=firebaseDatabase.getReference("MeterReadings");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList=new ArrayList<>();
        adapter=new recyclerview_reading(this,arrayList);
        recyclerView.setAdapter(adapter);
        HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewmeterreading.this, admindashboard.class);

                startActivity(intent);
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewmeterreading.this, admindashboard.class);

                startActivity(intent);
            }
        });
            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot node1 : snapshot.getChildren()) {
                        dSubmitreading dsubmit = node1.getValue(dSubmitreading.class);

                        arrayList.add(dsubmit);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


