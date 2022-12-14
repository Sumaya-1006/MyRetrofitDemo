package com.example.myretrofitdemo.utils;

import com.example.myretrofitdemo.Interface.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    public static final String BASE_URL = "https://api.unsplash.com";
    public static final String API_KEY = "GX-takjMz62_KdXS56lWwtHS7QFamcE9hJM1jkfPm7M";

    public static Retrofit retrofit = null;

    public static ApiInterface getApiInterface() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
       return retrofit.create(ApiInterface.class);
    }
}
