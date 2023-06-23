package com.energytool.igseapp;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.energytool.igseapp.databinding.ActivityEnergypricesettingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class energypricesetting extends AppCompatActivity {
    EditText mDayReading,mNightReading,mGasReading;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button btnchangePrice,GoHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energypricesetting);
        mDayReading = findViewById(R.id.updateday_reading);
        mNightReading = findViewById(R.id.updatenight_reading);
        mGasReading = findViewById(R.id.updategas_reading);
        btnchangePrice = findViewById(R.id.update_reading);
        GoHome=findViewById(R.id.go_home);
        GoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(energypricesetting.this, admindashboard.class);

                startActivity(intent);
            }
        });
        btnchangePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float dayReading = Float.parseFloat(mDayReading.getText().toString());
                float NightReading = Float.parseFloat(mNightReading.getText().toString());
                float gasReading = Float.parseFloat(mGasReading.getText().toString());

                updatePrice(dayReading, NightReading, gasReading);

            }
        });
    }
    private void
    updatePrice(float dayReading, float NightReading, float gasReading) {
        HashMap updatePrice = new HashMap();
        updatePrice.put("dayReading",dayReading);
        updatePrice.put("NightReading",NightReading);
        updatePrice.put("gasReading",gasReading);

        databaseReference = FirebaseDatabase.getInstance().getReference("price");
        databaseReference.updateChildren(updatePrice).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    mDayReading.setText("");
                    mGasReading.setText("");
                    mNightReading.setText("");

                    Toast.makeText(energypricesetting.this, "Price successfully updated", LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(energypricesetting.this, "Price not updated", LENGTH_SHORT).show();

                }
            }
        });
    }

    }


