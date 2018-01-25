package com.example.manne.bitcoinapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manne.bitcoinapi.Api.RestApi;
import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.priceDollar)
    TextView priceDollar;
    @BindView(R.id.nameCoin)
    TextView name;
    @BindView(R.id.symbolCoin)
    TextView symbol;
    @BindView(R.id.rankCoin)
    TextView rank;
    @BindView(R.id.priceBitCoin)
    TextView priceBit;
    BitCoin bitCoin;
    ArrayList<BitCoin> coins;
    RestApi api;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        api= new RestApi(this);
        final Intent intent = getIntent();
//        bitCoin = (BitCoin) intent.getSerializableExtra("EXTRA");
        Call <ArrayList<BitCoin>> call = api.getBitCoins(intent.getStringExtra("EXTRA"));
        call.enqueue(new Callback<ArrayList<BitCoin>>() {
            @Override
            public void onResponse(Call<ArrayList<BitCoin>> call, Response<ArrayList<BitCoin>> response) {
                if(response.isSuccessful()){
                    coins=response.body();
                    bitCoin=coins.get(0);
                    priceDollar.setText("Price USD: "+bitCoin.getPrice_usd());
                    priceBit.setText("Price BTC: "+bitCoin.getPrice_btc());
                    name.setText("Name: \n"+bitCoin.getName());
                    symbol.setText("Symbol: \n"+bitCoin.getSymbol());
                    rank.setText("Rank: "+bitCoin.getRank());
                    Picasso.with(MainActivity3.this)
                            .load("https://files.coinmarketcap.com/static/img/coins/64x64/"+bitCoin.getId()+".png")
                            .fit()
                            .into(imageView);
                }
                else{
                    Toast.makeText(MainActivity3.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BitCoin>> call, Throwable t) {
                Toast.makeText(MainActivity3.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });















    }
}
