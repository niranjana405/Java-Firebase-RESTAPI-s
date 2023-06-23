package com.energytool.igseapp;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;

public class submitmeterreading extends AppCompatActivity {
TextView TodayDate;
EditText DayReading,NightReading,GasReading;
Button GoHome;
Button SubmitReading;

TextView Customeremailid;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitmeterreading);
        DayReading=findViewById(R.id.day_reading);
        NightReading=findViewById(R.id.night_reading);
        GasReading=findViewById(R.id.gas_reading);
        SubmitReading=findViewById(R.id.submit_reading);
        Customeremailid=findViewById(R.id.customer_emailid);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("InitialReadings");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("MeterReadings");

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = root.child("customers").child(uid);

        uidRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("email_id").getValue(String.class);
                Customeremailid.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Calendar calendar= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
        }
        String todayDate= DateFormat.getDateInstance().format(calendar.getTime());
        TodayDate=findViewById(R.id.today_date);
        GoHome=findViewById(R.id.go_home);
        TodayDate.setText(todayDate);
        GoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(submitmeterreading.this, customerdashboard.class);
                startActivity(intent);
            }
        });
        SubmitReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dayreadings = DayReading.getText().toString();
                String nightreadings = NightReading.getText().toString();
                String gasreadings = GasReading.getText().toString();
                String date = TodayDate.getText().toString();
                String customerid = Customeremailid.getText().toString();

                if (TextUtils.isEmpty(dayreadings)) {
                    DayReading.setError("Day Reading is required");
                    Toast.makeText(submitmeterreading.this, "Please enter Day Reading", LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nightreadings)) {
                    NightReading.setError("Night Reading is required");
                    Toast.makeText(submitmeterreading.this, "Please enter Night Reading", LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gasreadings)) {
                    GasReading.setError("Gas Reading is required");
                    Toast.makeText(submitmeterreading.this, "Please enter Gas Reading", LENGTH_SHORT).show();
                    return;
                }
                FirebaseUserMetadata userdata = firebaseAuth.getCurrentUser().getMetadata();
                dSubmitreading intialreadings = new dSubmitreading(customerid, dayreadings, nightreadings, gasreadings, date);
                dSubmitreading meterreadings = new dSubmitreading(customerid, dayreadings, nightreadings, gasreadings, date);
                if (userdata.getCreationTimestamp() == userdata.getLastSignInTimestamp()) {
                    FirebaseDatabase.getInstance().getReference("InitialReadings").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(intialreadings).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(submitmeterreading.this, "Initial reading submitted successfully", LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), billgeneration.class));

                        }

                    });
                } else {
                    FirebaseDatabase.getInstance().getReference("MeterReadings").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(meterreadings).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(submitmeterreading.this, "Meter reading submitted successfully", LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), billgeneration.class));

                        }
                    });
                }
            }
        });

        }
    }
