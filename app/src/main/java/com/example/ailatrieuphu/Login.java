package com.example.ailatrieuphu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText tit_tk;
    TextInputEditText tit_mk;

    String urlduongdan="http://192.168.153.2:8080/GameLaravel/public/api/apinguoichoi";
   // String urlduongdan="http://192.168.1.253/GameLaravel/public/api/apinguoichoi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tit_tk=findViewById(R.id.ti_tendangnhap);
        tit_mk=findViewById(R.id.ti_matkhau);

    }
    public void Dangnhap(View view){

        String tendn = tit_tk.getText().toString();
        String mk = tit_mk.getText().toString();
        if(!tendn.isEmpty()|| !mk.isEmpty()) {
            GetAPINguoiChoi nguoiChoi = (GetAPINguoiChoi) new GetAPINguoiChoi(this, tendn, mk).execute(urlduongdan);
        }
        else {
            tit_tk.setError("Bạn chưa nhập tên tài khoản !");
            tit_mk.setError("Bạn chưa nhập mật khẩu !");
        }
    }
    public void Quenmatkhau(View view){
        Intent qmkintent = new Intent(Login.this,Forgetpassword.class);
        startActivity(qmkintent);

    }
    public void buttonDangky(View view){
        Intent dkintent = new Intent(Login.this,Registeraccount.class);
        startActivity(dkintent);
    }
}
