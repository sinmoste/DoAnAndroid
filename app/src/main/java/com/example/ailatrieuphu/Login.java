package com.example.ailatrieuphu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    TextInputEditText tit_tk;
    TextInputEditText tit_mk;
    private GoogleApiClient mGoogleApiClient;
    int RC_SIGN_IN=001;

    //String urlduongdan="http://192.168.153.2:8080/GameLaravel/public/api/apinguoichoi";
   // String urlduongdan="http://192.168.1.253/GameLaravel/public/api/apinguoichoi";
    String urlduongdan ="http://10.0.2.2:8000/api/apinguoichoi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tit_tk=findViewById(R.id.ti_tendangnhap);
        tit_mk=findViewById(R.id.ti_matkhau);

        //Yêu cầu người dùng cung cấp thông tin
        GoogleSignInOptions gso=new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //kết nối google API client
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        // Button Google Sig-in
        SignInButton signInButton=findViewById(R.id.btnSignIn);
        signInButton.setSize(signInButton.SIZE_STANDARD);

        findViewById(R.id.btnSignIn).setOnClickListener(this);


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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Failed",connectionResult+"");
    }
    //FUNCTION Dang Nhap
    private  void SigIn(){
        Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            Intent dkintent = new Intent(Login.this,MainActivity.class);
            startActivity(dkintent);

        }
    }
    private  void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount acct=result.getSignInAccount();
            //chõ này đăng nhập thành công
            // mày muốn lấy cái thông tin người dùng thì acc. là ra
            //vd  acct.getDisplayName()

        }
        else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                SigIn();
                break;
        }

    }
}
