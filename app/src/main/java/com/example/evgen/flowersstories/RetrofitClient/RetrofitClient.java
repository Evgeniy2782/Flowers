package com.example.evgen.flowersstories.RetrofitClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String ROOT_URL = "http://178.72.90.83:3001/";

    private static Retrofit retrofit() {

        return new Retrofit.Builder()

                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService(){
        return retrofit().create(ApiService.class);
    }
}

