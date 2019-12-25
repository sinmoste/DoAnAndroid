package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ailatrieuphu.Class.Adapter.NguoiChoiAdapter;
import com.example.ailatrieuphu.Class.LinhVuc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rank extends AppCompatActivity {

    public RecyclerView recyclerView;
    public NguoiChoiAdapter adapter;
    public ArrayList<NguoiChoi> listnguoichoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        Intent intent=getIntent();
        String jsonstring=intent.getStringExtra("NguoiChoi");
       // Toast.makeText(this,jsonstring,Toast.LENGTH_SHORT).show();
        if(getJson(jsonstring)){

        }
        else{
            Toast.makeText(this,"Get API Nguoi Choi failed",Toast.LENGTH_SHORT).show();
        }
//        int i=listnguoichoi.size();
//        String s=String.valueOf(i);
//        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        recyclerView=findViewById(R.id.rcl_rank);
        adapter=new NguoiChoiAdapter(this,listnguoichoi);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public boolean getJson(String jsonString){
        try{
            listnguoichoi =  new ArrayList<>();
            JSONArray jr = new JSONArray(jsonString);

            int length = jr.length();

            for(int i =0;i<length;i++){

                JSONObject jb = jr.getJSONObject(i);
                NguoiChoi nguoiChoi=new NguoiChoi();
                nguoiChoi.id=jb.getString("id");
                nguoiChoi.ten_dang_nhap=jb.getString("ten_dang_nhap");
                nguoiChoi.hinh_dai_dien=jb.getString("hinh_dai_dien");
                nguoiChoi.diem_cao_nhat=jb.getString("diem_cao_nhat");
                listnguoichoi.add(nguoiChoi);
//                listnguoichoi.add(new NguoiChoi(jb.getString("id"),jb.getString("ten_dang_nhap"),
//                        "","",jb.getString("hinh_dai_dien"),
//                        jb.getString("diem_cao_nhat"),""));
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
