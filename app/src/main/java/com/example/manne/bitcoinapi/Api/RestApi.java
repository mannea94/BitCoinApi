package com.example.manne.bitcoinapi.Api;

import android.content.Context;

import com.example.manne.bitcoinapi.BuildConfig;
import com.example.manne.bitcoinapi.LoggingInterceptor;
import com.example.manne.bitcoinapi.Model.BitCoin;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manne on 21.1.2018.
 */

public class RestApi {
    public static final int request_max_time_tn_seconds=20;

    public Context activity;

    public RestApi(Context activity){
        this.activity=activity;
    }

    public Retrofit getRetrofitInstance(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(request_max_time_tn_seconds, TimeUnit.SECONDS)
                .connectTimeout(request_max_time_tn_seconds, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
//                .baseUrl(ApiConstants.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public ApiService request(){
        return getRetrofitInstance().create(ApiService.class);
    }

    public Call<ArrayList<BitCoin>> getCoins(String convert, int limit){
        return request().getCoins(convert, limit);
    }

    public Call<ArrayList<BitCoin>> getBitCoins(String id, String convert){
        return request().getBitCoins(id, convert);
    }

//    public Call<ArrayList<BitCoin>> getCoinsLimit(){
//        return request().getCoinsLimit("convert", 50);
//    }

}
