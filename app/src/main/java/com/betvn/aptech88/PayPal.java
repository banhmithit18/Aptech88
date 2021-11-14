package com.betvn.aptech88;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PayPal extends AppCompatActivity {
    Button btn_pay_now;
    EditText edt_amount;
    String amount="";
    WebView wv1;
    String id="";
    ImageView imageview_back_transfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);
        wv1=findViewById(R.id.webView);
        edt_amount=findViewById(R.id.edt_amount);
        Intent intent= getIntent();
        id= intent.getExtras().getString("id_acc_to_paypal");
        wv1.setWebViewClient(new MyBrowser());
        imageview_back_transfer=findViewById(R.id.imageview_back_transfer);
        imageview_back_transfer.setOnClickListener(v -> {
                Intent intents=new Intent(getApplicationContext(),Transfer.class);
                intents.putExtra("id_account_home_transfer",id);
                startActivity(intents);
        });
        btn_pay_now=findViewById(R.id.btn_pay_now);

        btn_pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paypal();
            }
        });
    }
    private void get_walletid(String id) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/FindWallet";
        Map<String, String> params = new HashMap();
        params.put("id", id);
        JSONObject ids = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, ids, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    String id_wallet = response.getString("id");
                    create_payment(id_wallet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if (errorRes != null && errorRes.data != null) {
                    try {
                        stringData = new String(errorRes.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(PayPal.this, stringData, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MyRequestQueue.add(jsonRequest);
    }
    private void create_payment(String id_wallet) {
        String amount=edt_amount.getText().toString();
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/PaymentCreate";
        Map<String, String> params = new HashMap();
        params.put("paymentType", "deposit");
        params.put("promotionId", "1");
        params.put("amount", amount);
        params.put("walletId", id_wallet);
        JSONObject p = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, p, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if (errorRes != null && errorRes.data != null) {
                    try {
                        stringData = new String(errorRes.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(PayPal.this, stringData, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MyRequestQueue.add(jsonObjectRequest);
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void Paypal() {
        edt_amount=findViewById(R.id.edt_amount);
        String amount=edt_amount.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.7:8080/pay";
        Map<String, String> params = new HashMap();
        params.put("price", amount);
        params.put("currency", "USD");
        params.put("method", "paypal");
        params.put("intent", "sale");
        params.put("description", "thanks");
        JSONObject order = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, order, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                    Toast.makeText(PayPal.this, "fail", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                        wv1.getSettings().setLoadsImagesAutomatically(true);
                        wv1.getSettings().setJavaScriptEnabled(true);
                        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                        wv1.loadUrl(stringData);
                        get_walletid(id);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        requestQueue.add(jsonRequest);
    }
}