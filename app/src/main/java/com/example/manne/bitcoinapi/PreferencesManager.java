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



    private  static SharedPreferences getPreferences(Context c){
        return c.getApplicationContext().getSharedPreferences("MySharedPreferences", MainActivity.MODE_PRIVATE);
    }
}
