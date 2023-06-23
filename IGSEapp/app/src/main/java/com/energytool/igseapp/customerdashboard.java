package com.energytool.igseapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static android.widget.Toast.LENGTH_SHORT;


public class customerdashboard extends AppCompatActivity {
TextView signout,SubmitReading, ViewPAyBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdashboard);
        signout = findViewById(R.id.customer_signout);
        SubmitReading=findViewById(R.id.submit_reading);
        ViewPAyBill=findViewById(R.id.viewpay_bill);
        ViewPAyBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerdashboard.this, billgeneration.class);
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shapre=getSharedPreferences("remember",MODE_PRIVATE);
                SharedPreferences.Editor editor1=shapre.edit();
                editor1.putString("remember_me","false");
                editor1.apply();
                finish();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(customerdashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
        SubmitReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerdashboard.this, submitmeterreading.class);

                startActivity(intent);
            }
        });
    }
}
