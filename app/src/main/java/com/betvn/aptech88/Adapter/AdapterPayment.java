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
import com.betvn.aptech88.Model.Payment;
import com.betvn.aptech88.R;

import java.util.Date;
import java.util.List;

public class AdapterPayment extends ArrayAdapter<Payment> {
    Context context;
    int resource;
    List<Payment> paymentList;
    public AdapterPayment(Context context, int resource, List<Payment> paymentList)
    {
        super(context,resource,paymentList);
        this.context = context;
        this.resource = resource;
        this.paymentList = paymentList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,false);
        TextView date=view.findViewById(R.id.transfer_parameter);
        TextView method=view.findViewById(R.id.method);
        TextView amount=view.findViewById(R.id.amount);
        TextView status=view.findViewById(R.id.status);
        Payment payment = paymentList.get(position);
        method.setText(payment.getPaymentType());
        date.setText(payment.getPaymentDate());
        amount.setText(payment.getAmount());
        status.setText(payment.getStatus());
        return view;
    }
}
