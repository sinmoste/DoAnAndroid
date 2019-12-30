package com.example.ailatrieuphu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Manageraccount extends AppCompatActivity {
    Button btnCapnhat;
    public TextInputEditText txtEmail,txtMatkhau,txtNhaplaimatkhau,txtTendangnhap;
    public ProgressDialog pDialog;
    public ImageView imganhdd;
    //layhinh
    public static final String UPLOAD_KEY = "hinh_anh";
    public static final String UPLOAD_URL = "upload";
    final int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    private Bitmap bitmap;
    private static String hinhanh;
    public class URLs {
        private static final String ROOT_URL = "http://10.0.3.2:8000/api/cap-nhat-nguoi-choi";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageraccount);
        txtEmail=findViewById(R.id.ti_email);
        txtMatkhau=findViewById(R.id.ti_doimatkhau);
        txtNhaplaimatkhau=findViewById(R.id.ti_xacnhanmatkhau);
        imganhdd=findViewById(R.id.img_anhdaidien);
        btnCapnhat=findViewById(R.id.btn_capnhattt);

        //hiển thị tendangnhap
        txtTendangnhap=findViewById(R.id.ti_tendangnhap);
        final  String ten_dang_nhap = new CustomSharedpreferences(this).getShared("NguoiChoi","ten_dang_nhap");
       final String id = new CustomSharedpreferences(Manageraccount.this).getShared("NguoiChoi","id");
        txtTendangnhap.setText(ten_dang_nhap);


        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail=txtEmail.getText().toString().trim();
                String mMatkhau=txtMatkhau.getText().toString().trim();
                String mNhaplaimatkhau=txtNhaplaimatkhau.getText().toString().trim();
                String mAnhdaidien=hinhanh;
                String mid =id.trim();
                if(!mEmail.isEmpty()||!mMatkhau.isEmpty()||!mNhaplaimatkhau.isEmpty())
                {
                    if(mNhaplaimatkhau.equals(mMatkhau)) {
                        displayLoader();
                        CapNhat(mEmail,mMatkhau,mid,mAnhdaidien);
                    }
                    else
                    {
                        txtNhaplaimatkhau.setError("Mật khẩu nhập lại không trùng khớp!");
                        txtNhaplaimatkhau.requestFocus();
                    }
                }
                else
                {
                    txtEmail.setError("Bạn chưa nhập email!");
                    txtEmail.requestFocus();
                    txtMatkhau.setError("Bạn chưa nhập mật khẩu!");
                    txtMatkhau.requestFocus();
                    txtNhaplaimatkhau.setError("Mật khẩu nhập lại không trùng khớp!");
                    txtNhaplaimatkhau.requestFocus();
                    Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void displayLoader(){
        pDialog = new ProgressDialog(Manageraccount.this);
        pDialog.setMessage("Đang tải dữ liệu.. Vui lòng đợi...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    private void CapNhat(final String email,final String mk,final String id,final String hinhdaidien){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.ROOT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                //JSONObject jsonObject=new JSONObject(response);
                Toast.makeText(getApplicationContext(),"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Manageraccount.this,Infoaccount.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Cập nhật không thành công!"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("id",id);//id dau
                params.put("hinh_dai_dien",hinhdaidien);
                params.put("mat_khau",mk);
                params.put("email",email);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            // Lay Uri den file duoc chon
            filePath = data.getData();


            try {
                // Lay hinh anh Bitmap tu Uri
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                hinhanh=ReadAPI.encodeBitmapToString(bitmap);
                imganhdd.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void chonhinh_maa(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select image"),
                PICK_IMAGE_REQUEST);
    }

}
