package com.example.moviecatalog.rest;

import com.example.moviecatalog.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
@GET("/3/movie/{category}")
Call<Response> getMovie (
        @Path("category") String category,
        @Query("api_key") String api_key,
        @Query("page") int page
);
}
