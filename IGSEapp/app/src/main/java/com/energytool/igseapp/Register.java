package com.energytool.igseapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.google.android.gms.tasks.OnSuccessListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.HashMap;
import java.util.Map;
public class Register extends AppCompatActivity {

    EditText Email, Password, confirmpassword, address, propertytype, bedroom, energycode;
    Button RegisterButton,ScanQr;
    TextView loginLink;
    TextView qrtext;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    String userID;
    TextInputLayout textInput;
    private IntentIntegrator qrScan;
    AutoCompleteTextView autoComplete;
    String[] property = {"Detached", "Semi-Detached", "Terraced", "Flat", "Cottage", "Bungalow", "Mansion"};
    ArrayAdapter<String> property1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        qrtext=findViewById(R.id.qrtext1);
        Email = findViewById(R.id.emailid);
        Password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        address = findViewById(R.id.address);
        propertytype = findViewById(R.id.AutoCompleteTextView);
        bedroom = findViewById(R.id.bedroom);
        energycode = findViewById(R.id.energycode);
        RegisterButton = findViewById(R.id.registorbutton);
        ScanQr=findViewById(R.id.scanqr);
        loginLink = findViewById(R.id.loginlink);
        firebaseAuth = FirebaseAuth.getInstance();
         progressBar = findViewById(R.id.progressBar);
        databaseReference = FirebaseDatabase.getInstance().getReference("customers");

        autoComplete = findViewById(R.id.AutoCompleteTextView);
        property1 = new ArrayAdapter<String>(this, R.layout.list_property, property);
        autoComplete.setAdapter(property1);
        autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String propertylist = adapterView.getItemAtPosition(i).toString();
                //      Toast.makeText(Register.this, "Item: " + propertylist, Toast.LENGTH_SHORT).show();
            }
        });

        ScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, newqr.class);

                startActivity(intent);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);

                startActivity(intent);
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_id = Email.getText().toString();
                String password_cu = Password.getText().toString();
                String con_password = confirmpassword.getText().toString();
                String address_cu = address.getText().toString();
                String property_type = propertytype.getText().toString();
                int bedroom_no = Integer.parseInt(bedroom.getText().toString());
                String energy_code = energycode.getText().toString();
               /* if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong
                            Credentials",Toast.LENGTH_SHORT).show();
                }*/
                if (TextUtils.isEmpty(email_id)) {
                    Email.setError("Email is required");
                    Toast.makeText(Register.this, "Please enter email id", LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(password_cu)) {
                    Password.setError("Password is required");
                    Toast.makeText(Register.this, "Please enter password", LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(con_password)) {
                    Password.setError("Confirm Password is required");
                    Toast.makeText(Register.this, "Please enter confirm password", LENGTH_SHORT).show();
                    return;
                }
               /* if(password_cu!=con_password){
                    Password.setError("password does not match");
                    Toast.makeText(Register.this, "Please enter password and confirm password same", LENGTH_SHORT).show();
                    return;
                }*/
                if (TextUtils.isEmpty(address_cu)) {
                    Password.setError("Address is required");
                    Toast.makeText(Register.this, "Please enter your address", LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(property_type)) {
                    Password.setError("Property type is required");
                    Toast.makeText(Register.this, "Please enter property type details", LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(energy_code)) {
                    Password.setError("Enter 8 digit energy code ");
                    Toast.makeText(Register.this, "Please Enter 8 digit energy code", LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(bedroom.getText().toString())) {
                    bedroom.setError("Enter the bedroom no ");
                    Toast.makeText(Register.this, "Please Enter Bedroom details", LENGTH_SHORT).show();
                    return;
                }
                if (password_cu.length() < 6) {
                    Toast.makeText(Register.this, "Password must be more than 6 characters", LENGTH_SHORT).show();

                }
                if (energy_code.length() <8) {
                    Toast.makeText(Register.this, "Energy code must be more than 8 characters", LENGTH_SHORT).show();

                }
                progressBar.setVisibility(view.VISIBLE);
                if (password_cu.equals(con_password)) {


                    firebaseAuth.createUserWithEmailAndPassword(email_id, password_cu).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(view.GONE);

                            if (task.isSuccessful()) {
                                dModel customers1 = new dModel(email_id, password_cu, address_cu, property_type, bedroom_no, energy_code);
                                FirebaseDatabase.getInstance().getReference("customers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(customers1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(Register.this, "Registration successful", LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Login.class));

                                    }

                                });
                            }

                            else {

                                progressBar.setVisibility(view.INVISIBLE);


                               Toast toast = Toast.makeText(Register.this, task.getException().getMessage(),
                                      LENGTH_SHORT);
                              toast.show();
                                Log.i("test3x", task.getException().getMessage());
                            }
                            progressBar.setVisibility(view.INVISIBLE);
                        }
                    });
                }
                else
                {

                    Toast.makeText(Register.this, "password mismatch", LENGTH_SHORT).show();
                    progressBar.setVisibility(view.INVISIBLE);

                }
            }
        });
    }
}
