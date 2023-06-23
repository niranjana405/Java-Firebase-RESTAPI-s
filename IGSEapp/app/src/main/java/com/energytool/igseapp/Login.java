package com.energytool.igseapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.firestore.FirebaseFirestore;
import static android.widget.Toast.LENGTH_SHORT;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText Email, Password;
    ProgressBar progressbar;
    Button LoginButton;
    FirebaseAuth firebaseAuth;
    TextView registerlink;
    CheckBox RememberMe;
    static final String TAG="Register";
  //  public static final String SharedPRef="SharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences shapre=getSharedPreferences("remember",MODE_PRIVATE);
        String CheckBox= shapre.getString("remember_me"," ");
        if(CheckBox.equals("true")){
            startActivity(new Intent(getApplicationContext(),customerdashboard.class));

        }
        else if(CheckBox.equals("false")){
            Toast.makeText(Login.this,"Please sign in", LENGTH_SHORT).show();

        }



        Email = findViewById(R.id.emailid);
        Password = findViewById(R.id.password);
        LoginButton = findViewById(R.id.LoginButton);
        registerlink = findViewById(R.id.registerlink);
        firebaseAuth=FirebaseAuth.getInstance();
        progressbar=findViewById(R.id.progressBar);
        RememberMe=findViewById(R.id.remember_me);
        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id = Email.getText().toString();
                String password_cu = Password.getText().toString();
                if (TextUtils.isEmpty(email_id)) {
                    Email.setError("Email is required");
                    Toast.makeText(Login.this,"Please enter email id", LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(password_cu)) {
                    Password.setError("Password is required");
                    Toast.makeText(Login.this,"Please enter password", LENGTH_SHORT).show();
                    return;
                }
                if(password_cu.length()<6){
                    Toast.makeText(Login.this,"Password must be more than 6 characters", LENGTH_SHORT).show();

                }
                progressbar.setVisibility(view.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email_id,password_cu).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                        startActivity(new Intent(getApplicationContext(),customerdashboard.class));
                        }
                        else{
                            try {
                                throw task.getException();
                            }

                            catch (Exception e){
                                progressbar.setVisibility(View.INVISIBLE);
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(Login.this, e.getMessage(), LENGTH_SHORT).show();

                            }

                        }
                    }
                });
            } });
        RememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                             SharedPreferences shapre=getSharedPreferences("remember",MODE_PRIVATE);
                            SharedPreferences.Editor editor1=shapre.edit();
                            editor1.putString("remember_me","true");
                            editor1.apply();
                }
                else if(!compoundButton.isChecked()){
                    SharedPreferences shapre=getSharedPreferences("remember",MODE_PRIVATE);
                    SharedPreferences.Editor editor1=shapre.edit();
                    editor1.putString("remember_me","false");
                    editor1.apply();

                }
            }
        });

    }

}
