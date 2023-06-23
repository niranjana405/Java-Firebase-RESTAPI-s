package com.energytool.igseapp;


import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterfface {
    @GET( "/customer.json")
    Call<Apievent> getData();

}
