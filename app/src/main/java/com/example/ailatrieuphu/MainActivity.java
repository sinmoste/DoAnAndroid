package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Class.LinhVucAsyncTask;
import com.example.ailatrieuphu.Class.URLl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String urlduongdan="http://10.0.3.2:8000/api/nguoi-choi1";
    TextView tennc,credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tennc=findViewById(R.id.txt_tendangnhap);

        Intent intent = getIntent();
        String tdn=intent.getStringExtra("ten_dang_nhap");
        LoadThongTin(tdn);
    }
    public void Trochoimoi(View view){
        new LinhVucAsyncTask(this).execute(URLl.url_get_linh_vuc);
    }
    public void Lichsuchoi(View view){
        Intent intent = new Intent(MainActivity.this,History.class);
        startActivity(intent);
    }
    public void Quanlitaikhoan(View view){
        Intent intent = new Intent(MainActivity.this,Infoaccount.class);
        startActivity(intent);
    }
    public void Bangxephang(View view){
        Intent intent = new Intent(MainActivity.this,Rank.class);
        startActivity(intent);
    }
    public void Muacredit(View view){
        Intent intent = new Intent(MainActivity.this,Credit.class);
        startActivity(intent);
    }

    private void LoadThongTin(final String tdn)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlduongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String tendangnhap=jsonObject.getString("ten_dang_nhap");
                    Integer credit1=jsonObject.getInt("credit");
                    tennc.setText(tendangnhap);
                    credit.setText(credit1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("ten_dang_nhap",tdn);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
