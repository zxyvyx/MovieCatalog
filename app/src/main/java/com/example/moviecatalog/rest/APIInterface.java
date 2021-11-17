package com.example.moviecatalog.rest;

import com.example.moviecatalog.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/b/{api_key}")
    Call<Response> getMovie (
            @Path("api_key") String apikey
    );
}
