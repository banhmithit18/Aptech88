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

import com.betvn.aptech88.Model.Fixture;
import com.betvn.aptech88.Model.League;
import com.betvn.aptech88.R;

import java.util.List;

public class AdapterFixture extends ArrayAdapter<Fixture> {
    Context context;
    int resource;
    List<Fixture> fixtureList;
    public AdapterFixture(Context context, int resource, List<Fixture> fixtureList)
    {
        super(context,resource,fixtureList);
        this.context = context;
        this.resource = resource;
        this.fixtureList = fixtureList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,false);
        ImageView imageView_left=view.findViewById(R.id.imageview_left);
        ImageView imageView_right=view.findViewById(R.id.imageview_right);
        TextView name_lefts=view.findViewById(R.id.Name_Left);
        TextView name_rights=view.findViewById(R.id.Name_Right);
        Fixture fixture = fixtureList.get(position);
        name_lefts.setText(fixture.getHome());
        name_rights.setText(fixture.getAway());
        return view;
    }
}
