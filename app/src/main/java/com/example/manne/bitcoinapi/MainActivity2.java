package com.example.manne.bitcoinapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.manne.bitcoinapi.Adapter.CustomAdapter;
import com.example.manne.bitcoinapi.Api.RestApi;
import com.example.manne.bitcoinapi.Listener.OnRowClickListener2;
import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    RestApi api;
    ArrayList<BitCoin> bitCoins;
    BitCoin bitCoin;
    public BitCoinModel bitCoinModel;
    String convert;
    int limit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        api= new RestApi(this);
        convert=PreferencesManager.getConvert(MainActivity2.this);
        limit=PreferencesManager.getLimit(MainActivity2.this);
        bitCoinModel=PreferencesManager.getBitCoins(this);

        if(bitCoinModel==null) {
            bitCoinModel = new BitCoinModel();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        Call<ArrayList<BitCoin>> call = api.getCoins(convert, limit);
        call.enqueue(new Callback<ArrayList<BitCoin>>() {
            @Override
            public void onResponse(Call<ArrayList<BitCoin>> call, Response<ArrayList<BitCoin>> response) {
                if (response.isSuccessful()){
                    bitCoins=response.body();
                    customAdapter = new CustomAdapter(MainActivity2.this, bitCoins, new OnRowClickListener2() {
                        @Override
                        public void onRowClick(BitCoin bitCoin, int position) {
                            Intent intent = new Intent(MainActivity2.this, MainActivity4.class).putExtra("EXTRA2", bitCoins.get(position).getId());
//                            intent.putExtra("POS", position);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(customAdapter);
                }
                else{
                    Toast.makeText(MainActivity2.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BitCoin>> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Something went WRONG", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        PreferencesManager.addBitCoins(bitCoinModel, this);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }





}
