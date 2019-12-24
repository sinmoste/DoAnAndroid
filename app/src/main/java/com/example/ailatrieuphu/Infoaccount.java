package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;


import java.util.HashMap;
import java.util.Map;

public class Infoaccount extends AppCompatActivity {
    String tendangnhap_tt;
    String credit_tt;
    String email_tt;
    TextView tdn_tt,crd_tt,eml_tt;
    SharedPreferences mshared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoaccount);
        tdn_tt=findViewById(R.id.tv_tendangnhap_tt);
        eml_tt=findViewById(R.id.tv_email_tt);
        crd_tt=findViewById(R.id.tv_credit_tt);

        String ten_dang_nhap = new CustomSharedpreferences(this).getShared("NguoiChoi","ten_dang_nhap");
        String id = new CustomSharedpreferences(this).getShared("NguoiChoi","id");
        String credit = new CustomSharedpreferences(this).getShared("NguoiChoi","credit");
        String email = new CustomSharedpreferences(this).getShared("NguoiChoi","email");

        tdn_tt.setText(ten_dang_nhap);
        eml_tt.setText(email);
        crd_tt.setText(credit);

    }
    public void doimk(View view){
        Intent intent = new Intent(Infoaccount.this,Manageraccount.class);
        startActivity(intent);
    }
}
