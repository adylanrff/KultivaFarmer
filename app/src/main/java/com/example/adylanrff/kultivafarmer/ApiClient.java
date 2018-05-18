package com.example.adylanrff.kultivafarmer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adylan Roaffa on 8/21/2017.
 */

public class ApiClient {

    public final static String POPULARITY_DESC= "popularity.desc";
    public final static String TOP_RATED_DESC= "popularity.desc";


    public static final String BASE_URL = "http://168.235.103.57:5000/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}