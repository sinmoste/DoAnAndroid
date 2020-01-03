package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Class.QuenMatKhauAsynctask;
import com.example.ailatrieuphu.Class.URLl;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Forgetpassword extends AppCompatActivity {
    TextInputEditText tiTenDangNhap;
    TextInputEditText tiEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        tiTenDangNhap = findViewById(R.id.ti_tdnn);
        tiEmail = findViewById(R.id.ti_emaill);
    }

    public void LayMatKhau(View v){
        String tdn = tiTenDangNhap.getText().toString();
        String email = tiEmail.getText().toString();
        try {
            new QuenMatKhauAsynctask(this).execute(URLl.url_lay_mat_khau+"?email="+email+"&ten_dang_nhap="+tdn);

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
