package com.example.finallabmobile2024.API.Api;

import com.example.finallabmobile2024.Models.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("planetary/apod?api_key=VErvU0A1hMLLCn1kcJpFLlS66ZnX0F7rvuo80WrV")
    Call<Response> getData();
}
