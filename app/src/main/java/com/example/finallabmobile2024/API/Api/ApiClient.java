package com.example.finallabmobile2024.API.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static final String BASE_URL="https://api.nasa.gov/";

    static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
