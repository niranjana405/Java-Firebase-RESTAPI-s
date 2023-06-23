package com.energytool.igseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import static android.widget.Toast.LENGTH_SHORT;

public class admin extends AppCompatActivity {
    EditText admin_email,admin_password;
    Button admin_login;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin_email=findViewById(R.id.admin_email1);
        admin_password=findViewById(R.id.adminlogin_password);
        admin_login=findViewById(R.id.admin_LoginButton);
        firebaseDatabase=FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("admin-password");
        databaseReference.setValue("gse@energy");

         admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin_email1 = admin_email.getText().toString();
                String admin_password1 = admin_password.getText().toString();
                if (admin_email1.equals("gse@shangrila.gov.un") && admin_password1.equals("gse@energy")) {

                    Toast.makeText(admin.this, "you have signed successfully", LENGTH_SHORT).show();
                    Intent intent = new Intent(admin.this, admindashboard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(admin.this, "Email id and password is invalid", LENGTH_SHORT).show();

                }
            }
        });
    }
}
