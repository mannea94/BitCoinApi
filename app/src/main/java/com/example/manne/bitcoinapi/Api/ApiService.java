package com.example.manne.bitcoinapi.Api;

import com.example.manne.bitcoinapi.Model.BitCoin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by manne on 21.1.2018.
 */

public interface ApiService {
    @GET("ticker/")
    Call<ArrayList<BitCoin>> getCoins(@Query("convert") String convert, @Query("limit") int limit);

    @GET("ticker/{id}")
    Call<ArrayList<BitCoin>> getBitCoins(@Path("id") String id, @Query("convert") String convert);

//    @GET("ticker")
//    Call<ArrayList<BitCoin>> getCoinsLimit(@Query("convert") String convert, @Query("limit") int limit);

}
