package com.example.ailatrieuphu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.ChiTietLuotChoi;
import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.example.ailatrieuphu.Class.ThemCauHoiAsynctask;
import com.example.ailatrieuphu.Class.URLl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Answerquestion extends AppCompatActivity {


    TextView txtDiem,txtNoiDung,txtThuTuCH,txtTime,txtTen,txtCredit,txtLife;
    Button btnA,btnB,btnC,btnD;
    Button tgDoiCH,tg50,tgKhanGia,tgGoiDien,tgMuaCH; //tro giup theo thu tu trai -> phai
    private ArrayList<CauHoi> mCauHoi;
    String jsonString,dap_an;

    int position,diem,life,socaudung;

    ProgressBar mPg;
    ArrayList<ChiTietLuotChoi> mChiTiet;
    ArrayList<Integer> mRandom;

    CountDownTimer mCountdown;
    public Answerquestion() {
        mCauHoi =  new ArrayList<>();
        mRandom = new ArrayList<>();
        mChiTiet = new ArrayList<>();
        life = 5;
        socaudung=0;
        diem=0;
        position=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerquestion);

        Intent mIntent = getIntent();
        jsonString = mIntent.getStringExtra("message");

        txtLife=findViewById(R.id.txtLife);
        txtTen = findViewById(R.id.txtTenng);
        txtCredit=findViewById(R.id.txtCreditt);

        txtTen.setText(new CustomSharedpreferences(this).getShared("NguoiChoi","ten_nguoi_choi"));
        txtCredit.setText(new CustomSharedpreferences(this).getShared("NguoiChoi","credit"));
        txtLife.setText("x"+life);

        tgDoiCH = findViewById(R.id.btnDoiCauHoi);
        tg50 = findViewById(R.id.btn5050);
        tgKhanGia = findViewById(R.id.btnKhanGia);
        tgGoiDien = findViewById(R.id.btnGoiDien);
        tgMuaCH = findViewById(R.id.btnMuaCauHoi);


        txtDiem = findViewById(R.id.txtDiem);
        txtNoiDung = findViewById(R.id.txt_noidung);
        txtThuTuCH = findViewById(R.id.txt_Cauhoi);
        mPg = findViewById(R.id.progressBar);
        txtTime = findViewById(R.id.txtTime);
        mPg.setMax(100);
        btnA= findViewById(R.id.btn_a);
        btnB= findViewById(R.id.btn_b);
        btnC= findViewById(R.id.btn_c);
        btnD= findViewById(R.id.btn_d);
        if(getJson(jsonString))
             RandomCauHoi();
        loadQuestion();
        countddown(30000);
    }

    public void loadQuestion(){
        if (getJson(jsonString) && position < mCauHoi.size()) {
            txtNoiDung.setText(mCauHoi.get(mRandom.get(position)).getNoiDung());
            btnA.setText("A."+mCauHoi.get(mRandom.get(position)).getdA());
            btnB.setText("B."+mCauHoi.get(mRandom.get(position)).getdB());
            btnC.setText("C."+mCauHoi.get(mRandom.get(position)).getdC());
            btnD.setText("D."+mCauHoi.get(mRandom.get(position)).getdD());
            dap_an=mCauHoi.get(mRandom.get(position)).getDapAn();
            position++;

        } else {
            Toast.makeText(this, "Load API Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean ChonDung(int vitri,View view){
       if(life==0){

       }else {
           switch (view.getId()) {
               case R.id.btn_a:
                   if ("A".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem=diem+(500*(Integer.parseInt(txtTime.getText().toString())));
                       mChiTiet.add(new ChiTietLuotChoi(position,"A",diem));
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"A",diem));
                   break;
               case R.id.btn_b:
                   if ("B".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem=diem+(500*(Integer.parseInt(txtTime.getText().toString())));
                       mChiTiet.add(new ChiTietLuotChoi(position,"A",diem));
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"B",diem));
                   break;
               case R.id.btn_c:
                   if ("C".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem=diem+(500*(Integer.parseInt(txtTime.getText().toString())));
                       mChiTiet.add(new ChiTietLuotChoi(position,"A",diem));
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"C",diem));
                   break;
               case R.id.btn_d:
                   if ("D".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem=diem+(500*(Integer.parseInt(txtTime.getText().toString())));
                       mChiTiet.add(new ChiTietLuotChoi(position,"A",diem));
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"D",diem));
                   break;
           }
           life--;
           txtLife.setText("x"+life);
       }
        return false;
    } //Xu ly nut chon
    public  void Tieptuc(View view){
        HienThiNut();
        mCountdown.cancel();
        countddown(30000);
        if(ChonDung(position,view))
            socaudung++;

        try {
            if(life!=0) {
                txtDiem.setText(diem+"");
                txtLife.setText("x"+life);
                loadQuestion();
            }else{
                btnA.setClickable(false);
                txtDiem.setText(diem+"");
                txtLife.setText("x"+life);
                mCountdown.cancel();
                Map<String,String> map = new HashMap<>();
                map.put("nguoi_choi_id",new CustomSharedpreferences(this).getShared("NguoiChoi","id"));
                map.put("so_cau",Integer.toString(socaudung++));
                map.put("diem",Integer.toString(diem));
                new CustomSharedpreferences(this).addShared("LuotChoi",map);
                new ThemCauHoiAsynctask(this).execute(mChiTiet);
//                new CustomDialog(Answerquestion.this).showDialogandPostAPI("Hết sinh lực",
//                        "Số điểm của bạn là: "+diem+"\n"+"Nhấn OK để kết thúc",
//                        map,
//                        URLl.url_them_luot_choi,
//                        mChiTiet);
            }
        }catch (Exception e){
            Toast.makeText(this, "Hết câu!", Toast.LENGTH_SHORT).show();
        }
    }//Xu ly diem, qua cau khac

    /*Xu ly tro giup*/
    public void trogiupkhangia(View view)
    {

        switch (  mCauHoi.get(mRandom.get(position-1)).getDapAn()){
            case "A": new CustomDialog(this).showDialogaudience("A"); break;
            case "B": new CustomDialog(this).showDialogaudience("B"); break;
            case "C": new CustomDialog(this).showDialogaudience("C"); break;
            case "D": new CustomDialog(this).showDialogaudience("D"); break;
        }
        tgKhanGia.setVisibility(View.INVISIBLE);

    }
    public void RandomCauHoi(){
        for(int i=0;i<mCauHoi.size();i++){
            mRandom.add(i);
        }
        Collections.shuffle(mRandom);
    }
    public void Random50(View v){
        tg50.setVisibility(View.INVISIBLE);
        String a=mCauHoi.get(mRandom.get(position-1)).getDapAn();
        switch (a) {
            case "A":
                if (position % 3 == 0) {
                    btnC.setVisibility(View.INVISIBLE);
                    btnD.setVisibility(View.INVISIBLE);
                }
                else if (position % 3 == 1) {
                    btnD.setVisibility(View.INVISIBLE);
                    btnB.setVisibility(View.INVISIBLE);
                } else {
                    btnB.setVisibility(View.INVISIBLE);
                    btnC.setVisibility(View.INVISIBLE);
                }
                break;
            case "B":
                if (position % 3 == 0) {
                    btnA.setVisibility(View.INVISIBLE);
                    btnD.setVisibility(View.INVISIBLE);
                }
                else if (position % 3 == 1) {
                    btnD.setVisibility(View.INVISIBLE);
                    btnC.setVisibility(View.INVISIBLE);
                } else {
                    btnD.setVisibility(View.INVISIBLE);
                    btnA.setVisibility(View.INVISIBLE);
                }
                break;
            case "C":
                if (position % 3 == 0) {
                    btnA.setVisibility(View.INVISIBLE);
                    btnD.setVisibility(View.INVISIBLE);
                }
                else if (position % 3 == 1) {
                    btnD.setVisibility(View.INVISIBLE);
                    btnB.setVisibility(View.INVISIBLE);
                } else {
                    btnB.setVisibility(View.INVISIBLE);
                    btnA.setVisibility(View.INVISIBLE);
                }
                break;
            case "D":
                if (position % 3 == 0) {
                    btnC.setVisibility(View.INVISIBLE);
                    btnB.setVisibility(View.INVISIBLE);
                }
                else if (position % 3 == 1) {
                    btnA.setVisibility(View.INVISIBLE);
                    btnB.setVisibility(View.INVISIBLE);
                } else {
                    btnB.setVisibility(View.INVISIBLE);
                    btnC.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
    public void TroGiupDoiCauHoi(View v){
        tgDoiCH.setVisibility(View.INVISIBLE);
        position++;
        loadQuestion();
    }
    public void TroGiupQuaMan(View v){
        tgMuaCH.setVisibility(View.INVISIBLE);
        position++;
        diem++;
        loadQuestion();
    }
    public void TroGiupGoiDien(View v){
        tgGoiDien.setVisibility(View.INVISIBLE);
        new CustomDialog(this).showDialog("Người thân của bạn trả lời","Nên chọn đáp án "+ mCauHoi.get(position).getDapAn());
    }
    /*Ket thuc xu ly tro giup*/

    public void HienThiNut(){
        btnA.setVisibility(View.VISIBLE);
        btnB.setVisibility(View.VISIBLE);
        btnC.setVisibility(View.VISIBLE);
        btnD.setVisibility(View.VISIBLE);
    }
    //Countdown time
    public void countddown(long time){
        mCountdown = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                txtTime.setText(String.valueOf(l/1000));
                mPg.setProgress(Math.toIntExact(l / 300));
            }
            @Override
            public void onFinish() {
                txtTime.setText("0");
                mPg.setProgress(0);
                Map<String,String> map = new HashMap<>();
                map.put("nguoi_choi_id",new CustomSharedpreferences(Answerquestion.this).getShared("NguoiChoi","id"));
                map.put("so_cau",Integer.toString(socaudung++));
                map.put("diem",Integer.toString(diem));
                new ThemCauHoiAsynctask(Answerquestion.this).execute(mChiTiet);
            }
        }.start();
    }
    public boolean getJson(String jsonString){
        try{
            JSONObject data = new JSONObject(jsonString);
            JSONArray jr = data.getJSONArray("data");

            int length = jr.length();
            for(int i =0;i<length;i++){
                JSONObject jb = jr.getJSONObject(i);
                mCauHoi.add(new CauHoi(jb.getString("noi_dung"),jb.getInt("id"),
                        jb.getString("phuong_an_a"),
                        jb.getString("phuong_an_b"),
                        jb.getString("phuong_an_c"),
                        jb.getString("phuong_an_d"),jb.getString("dap_an")));
            }//(String noiDung, int ID, String dA, String dB, String dC, String dD, String dapAn)
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    } //Lay cau hoi
}
/*

*/

