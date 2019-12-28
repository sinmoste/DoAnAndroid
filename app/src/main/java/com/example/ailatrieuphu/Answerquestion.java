package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.ChiTietLuotChoi;
import com.example.ailatrieuphu.Class.Countdown;
import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.example.ailatrieuphu.Class.URLl;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Answerquestion extends AppCompatActivity {


    TextView txtDiem,txtNoiDung,txtThuTuCH,txtTime;
    Button btnA,btnB,btnC,btnD;
    Button btn50,btnKhanGia,btnQuaCauHoi,btnMuaCredit,btnDoiCauHoi;
   private ArrayList<CauHoi> mCauHoi;
    String jsonString,dap_an;
    int position=0,diem=0,life=5,socaudung=0;
    ProgressBar mPg;
    ArrayList<ChiTietLuotChoi> mChiTiet;
    ArrayList<Integer> mRandom;
    public Answerquestion() {
        mCauHoi =  new ArrayList<>();
        mRandom = new ArrayList<>();
        mChiTiet = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerquestion);

        Intent mIntent = getIntent();
        jsonString = mIntent.getStringExtra("message");

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
     new Countdown(this,mPg,txtTime,100).execute();
        loadQuestion();
    }

    public void loadQuestion(){
        if (getJson(jsonString) && position < mCauHoi.size()) {
            txtNoiDung.setText(mCauHoi.get(mRandom.get(position)).getNoiDung());
            btnA.setText(mCauHoi.get(mRandom.get(position)).getdA());
            btnB.setText(mCauHoi.get(mRandom.get(position)).getdB());
            btnC.setText(mCauHoi.get(mRandom.get(position)).getdC());
            btnD.setText(mCauHoi.get(mRandom.get(position)).getdD());
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
                       diem++;
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"A",diem));
                   break;
               case R.id.btn_b:
                   if ("B".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem++;
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"B",diem));
                   break;
               case R.id.btn_c:
                   if ("C".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem++;

                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"C",diem));
                   break;
               case R.id.btn_d:
                   if ("D".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem++;
                       return true;
                   }
                   mChiTiet.add(new ChiTietLuotChoi(position,"D",diem));
           }
           life--;
       }
        return false;
    } //Xu ly nut chon
    public  void Tieptuc(View view){
        Countdown mCount = (Countdown) new Countdown(this, mPg, txtTime, 333).execute();
        HienThiNut();
        if(ChonDung(position,view))
            socaudung++;
        mCount.cancel(true);
        try {
            if(life!=0) {
                loadQuestion();
            }else{

                Map<String,String> map = new HashMap<>();
                //map.put("nguoi_choi_id",new CustomSharedpreferences(this).getShared("NguoiChoi","id"));
                map.put("nguoi_choi_id","2");
                map.put("so_cau",Integer.toString(socaudung++));
                map.put("diem",Integer.toString(diem));
                new CustomSharedpreferences(this).addShared("LuotChoi",map);
                new CustomDialog(Answerquestion.this).showDialogandPostAPI("Hết sinh lực",
                        "Số điểm của bạn là: "+diem+"\n"+"Nhấn OK để kết thúc",
                        map,
                        URLl.url_them_luot_choi,
                        mChiTiet);


            }
        }catch (Exception e){
            Toast.makeText(this, "Hết câu!", Toast.LENGTH_SHORT).show();
        }
    }//Xu ly diem, qua cau khac

    /*Xu ly tro giup*/
    public void trogiupkhangia(View view)
    {
      new CustomDialog(this).showDialogaudience();
    }
    public void RandomCauHoi(){
        for(int i=0;i<mCauHoi.size();i++){
            mRandom.add(i);
        }
        Collections.shuffle(mRandom);
    }
    public void Random50(View v){
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
        position++;
        loadQuestion();
    }
    public void TroGiupQuaMan(View v){
        position++;
        diem++;
        loadQuestion();
    }
    /*Ket thuc xu ly tro giup*/

    public void HienThiNut(){
        btnA.setVisibility(View.VISIBLE);
        btnB.setVisibility(View.VISIBLE);
        btnC.setVisibility(View.VISIBLE);
        btnD.setVisibility(View.VISIBLE);
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

