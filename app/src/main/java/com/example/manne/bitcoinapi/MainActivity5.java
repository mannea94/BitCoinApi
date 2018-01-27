package com.example.manne.bitcoinapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.manne.bitcoinapi.Adapter.CustomAdapter2;
import com.example.manne.bitcoinapi.Api.RestApi;
import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity5 extends AppCompatActivity {

    @BindView(R.id.limit)
    RadioGroup limit;
    @BindView(R.id.limit10)
    RadioButton limit10;
    @BindView(R.id.limit50)
    RadioButton limit50;
    @BindView(R.id.limit100)
    RadioButton limit100;
    @BindView(R.id.limitALL)
    RadioButton limitALL;
    @BindView(R.id.buttonSAVE)
    ImageButton save;
    @BindView(R.id.currency)
    RadioGroup currency;
    @BindView(R.id.currencyUSD)
    RadioButton currencyUSD;
    @BindView(R.id.currencyEUR)
    RadioButton currencyEUR;
    RadioButton choice;
    RadioButton choice2;
    String limit2;
    String currency2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent.hasExtra("LIMIT1")){
            limit2=intent.getStringExtra("LIMIT1");
            if(limit2.equals("50")){
               limit50.setChecked(true);
                limit2="50";
            }

            if(limit2.equals("0")){
                limitALL.setChecked(true);
                limit2="0";
            }

            if(limit2.equals("100")){
                limit100.setChecked(true);
                limit2="100";
            }

            if(limit2.equals("10")){
                limit10.setChecked(true);
                limit2="10";
            }

        }

        if(intent.hasExtra("CURRENCY1")){
            currency2=intent.getStringExtra("CURRENCY1");
             if(currency2.equals("USD")){
                 currencyUSD.setChecked(true);
                 currency2="USD";
             }
             if(currency2.equals("EUR")){
                 currencyEUR.setChecked(true);
                 currency2="EUR";
             }

        }




        limit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                choice = (RadioButton) findViewById(selectedID);
                if(choice.getText().toString().equals("50")){
                    limit2="50";
                }

                if (choice.getText().toString().equals("all")) {
                    limit2 = "0";
                }

                if(choice.getText().toString().equals("100")){
                    limit2="100";
                }

                if(choice.getText().toString().equals("10")){
                    limit2="10";
                }

            }
        });


        currency.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                choice2= (RadioButton) findViewById(selectedID);

                if(choice2.getText().toString().equals("EUR")){
                    currency2=choice2.getText().toString();
                }
                if((choice2.getText().toString().equals("USD"))){
                    currency2=choice2.getText().toString();
                }

            }
        });

    }


    @OnClick(R.id.buttonSAVE)
    public void onClickSave(View view){
        PreferencesManager.addConvert(currency2, MainActivity5.this);
        PreferencesManager.addLimit(Integer.valueOf(limit2), MainActivity5.this);
        Intent intent = new Intent();
        intent.putExtra("LIMIT", limit2);
        intent.putExtra("CURRENCY", currency2);
        setResult(RESULT_OK, intent);
        finish();
    }

}
