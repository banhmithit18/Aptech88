package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionHistory extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        listView=findViewById(R.id.listview_transactionhistory);
        String Transfer_parameter[]={"123450331511313","5678945548445","98745","56123","32145"};
        String Method[]={"MomoPay","MomoPay","MomoPay","MomoPay","MomoPay"};
        String Amount[]={"50.000","100.000","50.000","100.000","200.000"};
        String status[]={"complete","complete","complete","complete","complete"};
        TransactionHistory.TransactionHistoryAdapter adapter=new TransactionHistory.TransactionHistoryAdapter(this,Transfer_parameter,Method,Amount,status);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Toast.makeText(TransactionHistory.this, "okela1", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(TransactionHistory.this, Matchdetails.class);
                    startActivity(myIntent);
                }
                if (position==1){
                    Toast.makeText(TransactionHistory.this, "okela2", Toast.LENGTH_SHORT).show();
                }
                if (position==2){
                    Toast.makeText(TransactionHistory.this, "okela3", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class TransactionHistoryAdapter extends ArrayAdapter<String> {
        Context context;
        String transfer_parameters[];
        String methods[];
        String amounts[];
        String statuss[];
         TransactionHistoryAdapter(Context c, String[] transfer_parameter, String[] method, String[] amount, String[] status) {
            super(c,R.layout.list_transaction_history,R.id.transfer_parameter,transfer_parameter);
            this.context=c;
            this.transfer_parameters=transfer_parameter;
            this.methods=method;
            this.amounts=amount;
            this.statuss=status;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.list_transaction_history,parent,false);
            TextView transfer_parameter=row.findViewById(R.id.transfer_parameter);
            TextView method=row.findViewById(R.id.method);
            TextView amount=row.findViewById(R.id.amount);
            TextView status=row.findViewById(R.id.status);
            transfer_parameter.setText(transfer_parameters[position]);
            method.setText(methods[position]);
            amount.setText(amounts[position]);
            status.setText(statuss[position]);
            return row;
        }
    }
}