package com.energytool.igseapp;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import static android.widget.Toast.LENGTH_SHORT;
import java.io.IOException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestAPI extends AppCompatActivity {
    Button SearchProperty,GoHome;
    TextView textviews;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);
        SearchProperty = findViewById(R.id.search_property);
        textviews = findViewById(R.id.textview5);
        GoHome=findViewById(R.id.go_back);
        GoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestAPI.this, MainActivity.class);

                startActivity(intent);
            }
        });

        SearchProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://igseapp-default-rtdb.firebaseio.com").addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIinterfface api = retrofit.create(APIinterfface.class);
                Call<Apievent> call = api.getData();
                call.enqueue(new Callback<Apievent>() {
                    @Override
                    public void onResponse(Call<Apievent> call, Response<Apievent> response) {
                        if (response.code() != 200) {
                            textviews.setText("Pls the internet connection");
                            return;
                        }
                    else {
                        String api1 =new Gson().toJson(response.body().getProperty_type());
                            String api2 =new Gson().toJson(response.body().getBedroom());
                            String api3 =new Gson().toJson(response.body().getUnit());
                            String api4 =new Gson().toJson(response.body().getAverage_electricity_gas_cost_per_day());
                            textviews.append(api1);
                            textviews.append(api2);
                            textviews.append(api3);
                            textviews.append(api4);

                        }

                    }

                    @Override
                    public void onFailure(Call<Apievent> call, Throwable t) {
                        Log.e("API", "Failed to fetch data", t);


                    }
                });
            }
        });
    }
}



