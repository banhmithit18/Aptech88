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

import com.betvn.aptech88.Leauge;
import com.betvn.aptech88.Model.League;
import com.betvn.aptech88.R;

import java.util.List;

public class AdapterLeague extends ArrayAdapter<League> {
    Context context;
    int resource;
    List<League> leagueList;
    public AdapterLeague(Context context, int resource, List<League> leagueList)
    {
        super(context,resource,leagueList);
        this.context = context;
        this.resource = resource;
        this.leagueList = leagueList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,false);
        ImageView imageView=view.findViewById(R.id.imageview_id);
        TextView name=view.findViewById(R.id.Name_T);
        League league = leagueList.get(position);
        name.setText(league.getName());
        return view;
    }
}
