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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registeraccount extends AppCompatActivity {
    Button btnDangKy;
    private TextInputEditText txttenTaiKhoan,txtmatKhau,txtnhapLaiMatKhau,txtemail;
    private ImageView imgv;
    private ProgressDialog pDialog;
    //layhinh
    public static final String UPLOAD_KEY = "hinh_anh";
    public static final String UPLOAD_URL = "upload";
    final int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    private Bitmap bitmap;
    private static String hinhanh;
    public class URLs {
        private static final String ROOT_URL = "http://10.0.3.2:8000/api/them-nguoi-choi";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeraccount);
        txttenTaiKhoan=findViewById(R.id.tidk_tendangnhap);
        txtmatKhau=findViewById(R.id.tidk_matkhau);
        txtnhapLaiMatKhau=findViewById(R.id.tidk_nhaplaimatkhau);
        txtemail=findViewById(R.id.tidk_email);
        btnDangKy=findViewById(R.id.btn_dangkytk);
        // Hien thi hinh anh len ImageView
        imgv = findViewById(R.id.imgHinhDaiDien);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mtenTaiKhoan=txttenTaiKhoan.getText().toString().trim();
                String mmatKhau=txtmatKhau.getText().toString().trim();
                String mnhapLaiMatKhau=txtnhapLaiMatKhau.getText().toString().trim();
                String memail=txtemail.getText().toString().trim();
                if(!mtenTaiKhoan.isEmpty() || !mmatKhau.isEmpty()|| !mnhapLaiMatKhau.isEmpty()|| !memail.isEmpty()) {
                    if(mnhapLaiMatKhau.equals(mmatKhau)) {
                        displayLoader();
                        DangKy(mtenTaiKhoan, mmatKhau, memail);
                    }
                    else
                    {
                        txtnhapLaiMatKhau.setError("Mật khẩu nhập lại không trùng khớp!");
                    }
                }
                else
                {
                    txttenTaiKhoan.setError("Tên đăng nhập không được để trống!");
                    txtmatKhau.setError("Mật khẩu không được để trống!");
                    txtnhapLaiMatKhau.setError("Mật khẩu nhập lại không được để trống!");
                    txtemail.setError("Mật khẩu không được để trống!");
                }
            }
        });
    }
    private void displayLoader(){
        pDialog = new ProgressDialog(Registeraccount.this);
        pDialog.setMessage("Đang tải dữ liệu.. Vui lòng đợi...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void DangKy(final String ttk,final String mk,final String email)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.ROOT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                        Toast.makeText(getApplicationContext(),"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Registeraccount.this,Login.class);
                        startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Đăng ký không thành công!1"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Đăng ký không thành công!"+error.toString(),Toast.LENGTH_SHORT).show();
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
                params.put("ten_dang_nhap",ttk);
                params.put("mat_khau",mk);
                params.put("email",email);
                params.put("hinh_dai_dien",hinhanh);
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
                imgv.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void chonhinh(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select image"),
                PICK_IMAGE_REQUEST);
    }

}
