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
import java.util.Collections;
import java.util.Comparator;

public class Rank extends AppCompatActivity {

    public RecyclerView recyclerView;
    public NguoiChoiAdapter adapter;
    public ArrayList<NguoiChoi> listnguoichoi,list2;

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
        sapxep();
        laytop10();
        recyclerView=findViewById(R.id.rcl_rank);
        adapter=new NguoiChoiAdapter(this,list2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void laytop10(){
        list2=new ArrayList<>();
        for(int i=0;i<10;i++){
           NguoiChoi nguoiChoi=new NguoiChoi();
            nguoiChoi.id=listnguoichoi.get(i).id;
            nguoiChoi.ten_dang_nhap=listnguoichoi.get(i).ten_dang_nhap;
            nguoiChoi.hinh_dai_dien=listnguoichoi.get(i).hinh_dai_dien;
            nguoiChoi.diem_cao_nhat=listnguoichoi.get(i).diem_cao_nhat;
            list2.add(nguoiChoi);
        }
    }
    public void sapxep(){
        Collections.sort(listnguoichoi, new Comparator<NguoiChoi>() {
            @Override
            public int compare(NguoiChoi o1, NguoiChoi o2) {
                int diem1,diem2;
                diem1=Integer.parseInt(o1.diem_cao_nhat);
                diem2=Integer.parseInt(o2.diem_cao_nhat);
                if(diem1<diem2){
                    return 1;
                }
                else{
                    if(diem1==diem2){
                        return 0;
                    }
                    else{
                        return -1;
                    }
                }
            }
        });
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
