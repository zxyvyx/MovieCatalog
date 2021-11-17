package com.example.moviecatalog.rest;

import com.example.moviecatalog.config.ServerConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = ServerConfig.API_ENDPOINT;
    private static Retrofit retrofit = null;

    public static Retrofit getCient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
