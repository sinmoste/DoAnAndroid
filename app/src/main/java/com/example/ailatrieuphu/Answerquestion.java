package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Answerquestion extends AppCompatActivity {

    private TextView tvch,tvid;
    private Button dapan1,dapan2,dapan3,dapan4;
    ArrayList<CauHoiItem> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerquestion);
        tvch=findViewById(R.id.txt_linhvuc);
        tvid=findViewById(R.id.txt_Cauhoi);
        dapan1=findViewById(R.id.btn_a);
        dapan2=findViewById(R.id.btn_b);
        dapan3=findViewById(R.id.btn_c);
        dapan4=findViewById(R.id.btn_d);
        try {
            // Đọc file: res/raw/company.json và trả về đối tượng Company.
            list = ReadJson.readJSONFile(this);
            tvid.setText("Câu hỏi số "+list.get(0).getID());
            tvch.setText(list.get(0).getNoidung());
            dapan1.setText("A: "+list.get(0).getPhuonganA());
            dapan2.setText("B: "+list.get(0).getPhuonganB());
            dapan3.setText("C: "+list.get(0).getPhuonganC());
            dapan4.setText("D: "+list.get(0).getPhuonganD());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void Run(View view) {
        try {
            // Đọc file: res/raw/company.json và trả về đối tượng Company.
            Random random=new Random();
            int i=random.nextInt(5);
            list = ReadJson.readJSONFile(this);
            tvid.setText("Câu hỏi số "+list.get(i).getID());
            tvch.setText(list.get(i).getNoidung());
            dapan1.setText("A: "+list.get(i).getPhuonganA());
            dapan2.setText("B: "+list.get(i).getPhuonganB());
            dapan3.setText("C: "+list.get(i).getPhuonganC());
            dapan4.setText("D: "+list.get(i).getPhuonganD());
        } catch (Exception e) {
            e.printStackTrace();
        }
}}
