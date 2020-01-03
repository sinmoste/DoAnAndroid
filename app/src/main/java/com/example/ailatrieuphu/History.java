package com.example.ailatrieuphu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Class.Adapter.LichSuMuaAdapter;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    public RecyclerView recyclerView;
    public LichSuMuaAdapter adapter;
    public ArrayList<LichSuMua> listls;
    String b;
    int a;
    TextView tennc,creditnc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent=getIntent();
        String jsonstring=intent.getStringExtra("lich_su_credit");
        String ten_dang_nhap= new CustomSharedpreferences(this).getShared("NguoiChoi","ten_dang_nhap");
        String credit= new CustomSharedpreferences(this).getShared("NguoiChoi","credit");

        tennc=findViewById(R.id.txtTennc);
        creditnc=findViewById(R.id.txtCreditnc);

        tennc.setText(ten_dang_nhap);
        creditnc.setText(credit);


        if(getJson(jsonstring)){
        }
        else{
            Toast.makeText(this,"Get API Nguoi Choi failed",Toast.LENGTH_SHORT).show();
        }

        recyclerView=findViewById(R.id.rcl_lichsumua);
        adapter=new LichSuMuaAdapter(this,listls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public boolean getJson(String jsonString){
        try{
            listls =  new ArrayList<>();
            JSONArray jr = new JSONArray(jsonString);

            int length = jr.length();

            for(int i =0;i<length;i++){

                JSONObject jb = jr.getJSONObject(i);
                LichSuMua lichSuMua=new LichSuMua();
                lichSuMua.id=jb.getString("id");
                lichSuMua.nguoi_choi_id=jb.getString("nguoi_choi_id");
                lichSuMua.goi_credit_id=jb.getString("goi_credit_id");
                lichSuMua.ten_goi=jb.getString("ten_goi");
                lichSuMua.credit=jb.getInt("credit");
                lichSuMua.so_tien=jb.getInt("so_tien");
                lichSuMua.create_at=jb.getString("created_at");
                listls.add(lichSuMua);
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
