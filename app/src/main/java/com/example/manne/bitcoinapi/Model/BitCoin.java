package com.example.manne.bitcoinapi.Model;

import java.io.Serializable;

/**
 * Created by manne on 21.1.2018.
 */

public class BitCoin implements Serializable {
    public String id;
    public String name;
    public double price_usd;
    public double price_btc;
    public double price_eur;
    public String symbol;
    public String rank;

    public BitCoin(){

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Double getPrice_btc() {
        return price_btc;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public String getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice_eur() {
        return price_eur;
    }

}
