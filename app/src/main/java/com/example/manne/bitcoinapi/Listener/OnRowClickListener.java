package com.example.manne.bitcoinapi.Listener;

import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;

/**
 * Created by manne on 22.1.2018.
 */

public interface OnRowClickListener {
    public void onRowClick(BitCoin bitCoin, int position);
    public void onLongRowClick(BitCoin bitCoin, int position);
}
