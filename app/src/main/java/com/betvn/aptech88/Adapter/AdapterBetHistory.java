package com.betvn.aptech88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betvn.aptech88.BetHistory;
import com.betvn.aptech88.Model.BetHistories;
import com.betvn.aptech88.R;

import java.util.List;

public class AdapterBetHistory extends ArrayAdapter<BetHistories> {
    Context context;
    int resource;
    List<BetHistories> betshistoryList;
    public AdapterBetHistory(Context context, int resource, List<BetHistories> betshistoryList)
    {
        super(context,resource,betshistoryList);
        this.context = context;
        this.resource = resource;
        this.betshistoryList = betshistoryList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,true);
        TextView obh=view.findViewById(R.id.odd_bet_history);
        TextView bah=view.findViewById(R.id.bet_amount_history);
        TextView rb=view.findViewById(R.id.result_bet);
        BetHistories betHistories = betshistoryList.get(position);
        obh.setText(betHistories.getOdd());
        bah.setText(betHistories.getBet_amount());
        rb.setText(betHistories.getResult());
        return view;
    }
}
