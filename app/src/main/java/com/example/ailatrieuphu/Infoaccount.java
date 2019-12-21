package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Infoaccount extends AppCompatActivity {
    String tendangnhap_tt;
    String credit_tt;
    String email_tt;
    TextView tdn_tt,crd_tt,eml_tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoaccount);
        tdn_tt=findViewById(R.id.tv_tendangnhap_tt);
        eml_tt=findViewById(R.id.tv_email_tt);
        crd_tt=findViewById(R.id.tv_credit_tt);

        Intent intent = getIntent();
        tendangnhap_tt=intent.getStringExtra("ten_dang_nhap");
        tdn_tt.setText("Tên Đăng Nhập :"+tendangnhap_tt);
    }
    public void doimk(View view){
        Intent intent = new Intent(Infoaccount.this,Manageraccount.class);
        startActivity(intent);
    }
}
