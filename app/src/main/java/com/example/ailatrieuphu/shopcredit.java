package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ailatrieuphu.Class.Adapter.CreditAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class shopcredit extends AppCompatActivity {

    public RecyclerView recyclerView;
    public CreditAdapter adapter;
    public ArrayList<CreDit> listcredit;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcredit);
        Intent intent=getIntent();
        String jsonstring=intent.getStringExtra("credit");
        Toast.makeText(this,jsonstring,Toast.LENGTH_SHORT).show();
        if(getJson(jsonstring)){

         }else {
            Toast.makeText(this, "Get API Nguoi Choi failed", Toast.LENGTH_SHORT).show();
        }



        recyclerView=findViewById(R.id.rcl_goicredit);
        adapter=new CreditAdapter(this,listcredit);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public boolean getJson(String jsonString){
        try{
            listcredit =  new ArrayList<>();
           // JSONObject data = new JSONObject(jsonString);
            JSONObject data=new JSONObject(jsonString);

            JSONArray jr = data.getJSONArray("data");

            int length = jr.length();
            a=length;
            Toast.makeText(this,String.valueOf(length),Toast.LENGTH_SHORT).show();
            for(int i =0;i<length;i++){
                JSONObject jb = jr.getJSONObject(i);
                CreDit creDit=new CreDit();
                creDit.id=jb.getString("id");
                creDit.ten_goi_credit=jb.getString("ten_goi");
                creDit.gia_credit=jb.getInt("credit");
                creDit.gia_tien_credit=jb.getInt("so_tien");
                listcredit.add(creDit);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
