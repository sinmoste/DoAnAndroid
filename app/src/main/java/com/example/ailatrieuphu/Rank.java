package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;

public class Rank extends AppCompatActivity {
    TextView txtTen,txtCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        txtTen = findViewById(R.id.txtTenng);
        txtCredit=findViewById(R.id.txtCreditt);

        txtTen.setText(new CustomSharedpreferences(this).getShared("NguoiChoi","ten_nguoi_choi"));
        txtCredit.setText(new CustomSharedpreferences(this).getShared("NguoiChoi","credit"));
    }
}
