package com.example.finallabmobile2024.API.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("planetary/apod?api_key=EdXtqmYNFhjsKHnYQ1bKc8sdz5EUDOknidZbnRSQ")
    Call<Response> getData();
}
