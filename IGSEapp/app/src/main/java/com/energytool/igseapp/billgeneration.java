package com.energytool.igseapp;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import android.os.Bundle;
import android.widget.ProgressBar;


public class billgeneration extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    TextView Customeremailid, currentGasreading, PreviousGasReading, currElecDay, currElecNight;
    TextView prevElecNight, prevElecDay, tariffElecDay, tariffElecNight, tariffGas, StdCharge, BillAmount;
    Button PayBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billgeneration);
        firebaseAuth = FirebaseAuth.getInstance();
        currentGasreading = findViewById(R.id.current_gasreading);
        databaseReference = FirebaseDatabase.getInstance().getReference("InitialReadings");
        databaseReference = FirebaseDatabase.getInstance().getReference("MeterReadings");
        Customeremailid = findViewById(R.id.customer_emailid1);
        PayBill=findViewById(R.id.paybill);
        currentGasreading = findViewById(R.id.current_gasreading);
        PreviousGasReading = findViewById(R.id.previous_gasreading);
        prevElecNight = findViewById(R.id.previous_electricityNight);
        prevElecDay = findViewById(R.id.previous_electricityDay);
        currElecDay = findViewById(R.id.current_electricityDay);
        currElecNight = findViewById(R.id.current_electricityNight);
        tariffElecDay = findViewById(R.id.tariff_electricityDay);
        tariffElecNight = findViewById(R.id.tariff_electricityNight);
        tariffGas = findViewById(R.id.tariff_gas);
        BillAmount = findViewById(R.id.total_amount);
        StdCharge = findViewById(R.id.standard_charge);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String uid1 = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference root1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference root2 = FirebaseDatabase.getInstance().getReference();

        DatabaseReference root3 = FirebaseDatabase.getInstance().getReference();


        DatabaseReference uidRef = root.child("customers").child(uid);
        DatabaseReference uidRef1 = root1.child("InitialReadings").child(uid1);
        DatabaseReference uidRef2 = root2.child("MeterReadings").child(uid2);

        DatabaseReference uidRef3 = FirebaseDatabase.getInstance().getReference("price");
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
        uidRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String initialGasRead = snapshot.child("gasreadings").getValue(String.class);
                String initialElectricityDay = snapshot.child("dayreadings").getValue(String.class);
                String initialElectricityNight = snapshot.child("nightreadings").getValue(String.class);
                prevElecDay.setText(initialElectricityDay);
                PreviousGasReading.setText(initialGasRead);
                prevElecNight.setText(initialElectricityNight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        uidRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String currentGasRead = snapshot.child("gasreadings").getValue(String.class);
                String currentElectricityDay = snapshot.child("dayreadings").getValue(String.class);
                String currentElectricityNight = snapshot.child("nightreadings").getValue(String.class);
                currentGasreading.setText(currentGasRead);
                currElecDay.setText(currentElectricityDay);
                currElecNight.setText(currentElectricityNight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        uidRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String priceGasRead = snapshot.child("gasReading").getValue().toString();
                String priceElecDay = snapshot.child("dayReading").getValue().toString();
                String priceElecNight = snapshot.child("NightReading").getValue().toString();

                tariffGas.setText(priceGasRead);
                tariffElecDay.setText(priceElecDay);
                tariffElecNight.setText(priceElecNight);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}

