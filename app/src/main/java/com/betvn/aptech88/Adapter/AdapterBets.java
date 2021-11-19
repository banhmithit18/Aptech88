package com.betvn.aptech88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betvn.aptech88.Model.Bets;
import com.betvn.aptech88.Model.League;
import com.betvn.aptech88.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBets extends ArrayAdapter<Bets> {
    Context context;
    int resource;
    List<Bets> betsList;
    public AdapterBets(Context context, int resource, List<Bets> betsList)
    {
        super(context,resource,betsList);
        this.context = context;
        this.resource = resource;
        this.betsList = betsList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,true);
        TextView name_bets=view.findViewById(R.id.name_bets);
        TextView value=view.findViewById(R.id.value);
        TextView odd=view.findViewById(R.id.odd);
        Bets bets = betsList.get(position);
        name_bets.setText(bets.getName_bet());
        value.setText(bets.getValue());
        odd.setText(bets.getOdd());
        return view;
    }
}
