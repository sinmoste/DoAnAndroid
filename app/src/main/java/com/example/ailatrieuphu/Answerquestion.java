package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.URLl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Answerquestion extends AppCompatActivity {


    TextView txtDiem,txtNoiDung,txtThuTuCH;
    Button btnA,btnB,btnC,btnD;
    Button btn50,btnKhanGia,btnQuaCauHoi,btnMuaCredit,btnDoiCauHoi;
   private ArrayList<CauHoi> mCauHoi;
    String jsonString,dap_an;
    int position=0,diem=0,life=5;


    ProgressBar mPg;
    SharedPreferences sharedPreferences;

    public Answerquestion() {
        mCauHoi =  new ArrayList<>();
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

        btnA= findViewById(R.id.btn_a);
        btnB= findViewById(R.id.btn_b);
        btnC= findViewById(R.id.btn_c);
        btnD= findViewById(R.id.btn_d);

        loadQuestion();
    }

//    public void test(){
////        Map<String, String> params=new HashMap<>();
////        params.put("nguoi_choi_id", String.valueOf(1));
////        params.put("so_cau", String.valueOf(1));
////        params.put("diem", String.valueOf(1));
////        new CustomSharedpreferences(this).addShared(sharedPreferences,"LuotChoi",params);
////        String a = new CustomSharedpreferences(this).getShared(sharedPreferences,"LuotChoi","nguoi_choi_id");
////        txtDiem.setText(a);
////    }

    public void loadQuestion(){
        if (getJson(jsonString) && position < mCauHoi.size()) {
            txtNoiDung.setText(mCauHoi.get(position).getNoiDung());
            btnA.setText(mCauHoi.get(position).getdA());
            btnB.setText(mCauHoi.get(position).getdB());
            btnC.setText(mCauHoi.get(position).getdC());
            btnD.setText(mCauHoi.get(position).getdD());
            dap_an=mCauHoi.get(position).getDapAn();
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
        ChonDung(position,view);
        try {
            if(life!=0) {
                loadQuestion();
            }else{
                mDialog("Hết mạng rồi","Số điểm của bạn là: "+diem+"\n"+"Nhấn OK để kết thúc");
            }
        }catch (Exception e){
            Toast.makeText(this, "Hết câu!", Toast.LENGTH_SHORT).show();
        }
    }//Xu ly diem, qua cau khac



    public void mDialog(String title,String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Them luot choi
                        Map<String,String> map = new HashMap<>();
                        map.put("nguoi_choi_id","2");
                        map.put("so_cau","2");
                        map.put("diem","2");
                        ReadAPI.PostAPI(Answerquestion.this,  map, URLl.url_them_luot_choi);
                        finish();
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                //.setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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

