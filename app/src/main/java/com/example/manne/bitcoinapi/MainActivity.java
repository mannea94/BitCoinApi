package com.example.manne.bitcoinapi;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.manne.bitcoinapi.Adapter.CustomAdapter2;
import com.example.manne.bitcoinapi.Listener.OnRowClickListener;
import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fab)
    FloatingActionButton floatButton;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
//    @BindView(R.id.action_settings)
//    MenuItem item;
    BitCoinModel bitCoinModel;
    CustomAdapter2 customAdapter2;
    BitCoin bitCoin;
    int pos;
    static int REQUEST_CODE=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bitCoinModel=PreferencesManager.getBitCoins(this);
        if(bitCoinModel==null) {
            bitCoinModel = new BitCoinModel();
        }
        customAdapter2= new CustomAdapter2(MainActivity.this, bitCoinModel, new OnRowClickListener() {
            @Override
            public void onRowClick(BitCoin bitCoin, int position) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class).putExtra("EXTRA", bitCoinModel.favorites.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongRowClick(BitCoin bitCoin, final int position) {
                final AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setMessage("Are you sure to delete this item?");
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        bitCoinModel=PreferencesManager.getBitCoins(MainActivity.this);
                        bitCoinModel.favorites.remove(position);
                        PreferencesManager.addBitCoins(bitCoinModel, MainActivity.this);
                        customAdapter2.setItems(getList());
                        customAdapter2.notifyDataSetChanged();

                    }
                });
                myBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                myBuilder.create().show();
            }


        });

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView2.setAdapter(customAdapter2);


    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        customAdapter2.setItems(getList());
//        customAdapter2.notifyDataSetChanged();
//    }

    ArrayList<BitCoin> getList(){
        BitCoinModel bitCoinModel = PreferencesManager.getBitCoins(this);
        return bitCoinModel.favorites;
    }

    @OnClick(R.id.fab)
    public void onClickFloat(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE && resultCode == RESULT_OK){
            customAdapter2.setItems(getList());
            customAdapter2.notifyDataSetChanged();
//            recyclerView2.setAdapter(customAdapter2);
        }

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
}
//    @OnClick(R.id.action_settings)
//    public void onClickSettings(View view){
//        Intent intent = new Intent(MainActivity.this, MainActivity5.class);
//        startActivity(intent);
//    }
}
