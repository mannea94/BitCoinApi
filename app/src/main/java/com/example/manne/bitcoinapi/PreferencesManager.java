package com.example.manne.bitcoinapi;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by manne on 21.1.2018.
 */

public class PreferencesManager {

    public static void addBitCoins(BitCoinModel user, Context c){
        Gson gson = new Gson();
        String mapString = gson.toJson(user);
        getPreferences(c).edit().putString("bitCoin", mapString).apply();
    }

    public static BitCoinModel getBitCoins(Context c){
        return new Gson().fromJson(getPreferences(c).getString("bitCoin",null), BitCoinModel.class);
    }

    public static void addConvert(String convert, Context c){

        getPreferences(c).edit().putString("convert", convert).apply();
    }

    public static String getConvert(Context c){
        return  getPreferences(c).getString("convert", "USD");
    }

    public static void addLimit(int limit, Context c){
        getPreferences(c).edit().putInt("limit", limit).apply();
    }

    public static int getLimit(Context c){
        return  getPreferences(c).getInt("limit", 0);
    }


    private  static SharedPreferences getPreferences(Context c){
        return c.getApplicationContext().getSharedPreferences("MySharedPreferences", MainActivity.MODE_PRIVATE);
    }
}
