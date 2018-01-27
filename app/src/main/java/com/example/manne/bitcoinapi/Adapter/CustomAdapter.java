package com.example.manne.bitcoinapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manne.bitcoinapi.MainActivity2;
import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Listener.OnRowClickListener2;
import com.example.manne.bitcoinapi.PreferencesManager;
import com.example.manne.bitcoinapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manne on 21.1.2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    List<BitCoin> bitCoins = new ArrayList<>();
    OnRowClickListener2 onRowClickListener2;


    public CustomAdapter(Context _context, ArrayList<BitCoin> bitCoin, OnRowClickListener2 onRowClickListener2_) {
        context=_context;
        bitCoins=bitCoin;
        onRowClickListener2=onRowClickListener2_;
    }

    public void setItems(List<BitCoin> coins){
        bitCoins=coins;
    }





    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate the custom layout
        View view = inflater.inflate(R.layout.recycler_view_raw, parent, false);
        //Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BitCoin bitCoin = bitCoins.get(position);
        Picasso.with(context)
                .load("https://files.coinmarketcap.com/static/img/coins/64x64/"+bitCoin.getId()+".png")
                .fit()
                .into(holder.imageView);
        holder.convert=PreferencesManager.getConvert(context);
        if(holder.convert.equals("EUR")) {
            holder.priceDolar.setText(bitCoin.getPrice_eur().toString());
        }
        if(holder.convert.equals("USD")){
            holder.priceDolar.setText(bitCoin.getPrice_usd().toString());
        }
        holder.priceBitCoin.setText(bitCoin.getPrice_btc().toString());
        holder.nameCoin.setText(bitCoin.getName());
        holder.rank=bitCoin.getRank();
        holder.symbol=bitCoin.getSymbol();
        holder.buttonADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity2)context).bitCoinModel.favorites.add(bitCoin);

//                ((MainActivity2)context).index=position;
//                bitCoinModel.bitCoins.add(bitCoin);
//                PreferencesManager.addBitCoins(bitCoinModel, context);
//                onRowClickListener.onRowClick(bitCoin, position);

                //                Intent intent = new Intent(context, MainActivity.class);
//                intent.putExtra("EXTRA", bitCoinModel.bitCoins.add(bitCoin));
//                intent.putExtra("POSITION", position);
//                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRowClickListener2.onRowClick(bitCoin, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bitCoins.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageRaw)
        ImageView imageView;
        @BindView(R.id.priceDolar)
        TextView priceDolar;
        @BindView(R.id.priceBitCoin)
        TextView priceBitCoin;
        @BindView(R.id.buttonADD)
        ImageButton buttonADD;
        @BindView(R.id.nameCoin)
        TextView nameCoin;
        String rank;
        String symbol;
        String convert;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
