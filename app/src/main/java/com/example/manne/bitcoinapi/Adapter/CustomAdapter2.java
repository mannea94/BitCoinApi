package com.example.manne.bitcoinapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manne.bitcoinapi.Model.BitCoin;
import com.example.manne.bitcoinapi.Model.BitCoinModel;
import com.example.manne.bitcoinapi.Listener.OnRowClickListener;
import com.example.manne.bitcoinapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manne on 21.1.2018.
 */

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {
    Context context;
    BitCoinModel bitCoinModel = new BitCoinModel();
    OnRowClickListener onRowClickListener;


    public CustomAdapter2(Context _context, BitCoinModel bitCoinModel1, OnRowClickListener onRowClickListener1) {
        context=_context;
        bitCoinModel=bitCoinModel1;
        onRowClickListener=onRowClickListener1;
    }

    public void setItems(ArrayList<BitCoin> coins){
        bitCoinModel.favorites=coins;
//        notifyDataSetChanged();
    }





    @Override
    public CustomAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate the custom layout
        View view = inflater.inflate(R.layout.recycler_view_raw2, parent, false);
        //Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BitCoin bitCoin = bitCoinModel.favorites.get(position);
        Picasso.with(context)
                .load("https://files.coinmarketcap.com/static/img/coins/64x64/"+bitCoin.getId()+".png")
                .fit()
                .into(holder.imageView);
        holder.priceDolar.setText(bitCoin.getPrice_usd().toString());
        holder.priceBitCoin.setText(bitCoin.getPrice_btc().toString());
        holder.nameCoin.setText(bitCoin.getName());
        holder.rank=bitCoin.getRank();
        holder.symbol=bitCoin.getSymbol();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRowClickListener.onRowClick(bitCoin, position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onRowClickListener.onLongRowClick(bitCoin, position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return bitCoinModel.favorites.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageRaw2)
        ImageView imageView;
        @BindView(R.id.priceDolar2)
        TextView priceDolar;
        @BindView(R.id.priceBitCoin2)
        TextView priceBitCoin;
        @BindView(R.id.nameCoin)
        TextView nameCoin;
        String rank;
        String symbol;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
