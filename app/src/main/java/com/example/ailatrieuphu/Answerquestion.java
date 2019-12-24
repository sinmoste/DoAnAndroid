package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.Countdown;
import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.example.ailatrieuphu.Class.URLl;

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
    private Countdown mCount;
    ProgressBar mPg;

    ArrayList<Integer> mRandom;
    public Answerquestion() {
        mCauHoi =  new ArrayList<>();
        mRandom = new ArrayList<>();
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
                   break;
               case R.id.btn_b:
                   if ("B".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem++;
                       return true;
                   }
                   break;
               case R.id.btn_c:
                   if ("C".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem++;
                       return true;
                   }
                   break;
               case R.id.btn_d:
                   if ("D".equals(mCauHoi.get(vitri).getDapAn())) {
                       diem++;
                       return true;
                   }
                   break;
           }
           life--;
       }
        return false;
    } //Xu ly nut chon
    public  void Tieptuc(View view){
        mCount= (Countdown) new Countdown(this,mPg,txtTime,333).execute();
        if(ChonDung(position,view))
            socaudung++;
        try {
            if(life!=0) {
                loadQuestion();
            }else{
                Map<String,String> map = new HashMap<>();
                //map.put("nguoi_choi_id",new CustomSharedpreferences(this).getShared("NguoiChoi","id"));
                map.put("nguoi_choi_id","1");
                map.put("so_cau",Integer.toString(socaudung++));
                map.put("diem",Integer.toString(diem));
                new CustomSharedpreferences(this).addShared("LuotChoi",map);
                new CustomDialog(Answerquestion.this).showDialogandPostAPI("Hết sinh lực",
                        "Số điểm của bạn là: "+diem+"\n"+"Nhấn OK để kết thúc",
                        map,
                        URLl.url_them_luot_choi);
            }
        }catch (Exception e){
            Toast.makeText(this, "Hết câu!", Toast.LENGTH_SHORT).show();
        }
    }//Xu ly diem, qua cau khac

    public void Ramdom5050(){

    }

    public void RandomCauHoi(){
        for(int i=0;i<mCauHoi.size();i++){
            mRandom.add(i);
        }
        Collections.shuffle(mRandom);
    }

//    private void DangKy(final int pID, final int pList, final int score)
//    {
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLl.url_them_luot_choi, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Fao;ed"+e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"API Failed"+error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        })
//        {
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params=new HashMap<>();
//                params.put("nguoi_choi_id", String.valueOf(pID));
//                params.put("so_cau", String.valueOf(pList));
//                params.put("diem", String.valueOf(score));
//                return params;
//
//            }
//        };
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//
//        requestQueue.add(stringRequest);
//    }

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

