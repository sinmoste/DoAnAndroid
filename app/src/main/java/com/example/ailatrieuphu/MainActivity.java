package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ailatrieuphu.Class.LinhVucAsyncTask;
import com.example.ailatrieuphu.Class.NguoiChoiAsysTask;
import com.example.ailatrieuphu.Class.ShopcreditAsynctask;
import com.example.ailatrieuphu.Class.URLl;


public class MainActivity extends AppCompatActivity {
    String urlduongdan="http://10.0.3.2:8000/api/nguoi-choi1";
    TextView tennc,creditnc;
    String ten_dang_nhap;
    String credit;
    ImageView imgamthanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tennc=findViewById(R.id.txtTenNguoiChoi);
        creditnc=findViewById(R.id.txtCredit);
        //thông tin người chơi
        Intent intent = getIntent();
        ten_dang_nhap=intent.getStringExtra("ten_dang_nhap");
        tennc.setText(ten_dang_nhap);
        credit= intent.getStringExtra("credit");
        creditnc.setText(credit);
        //âm thanh nền
        imgamthanh= findViewById(R.id.img_amthanh);
        final MediaPlayer mediaPlayer =MediaPlayer.create(this,R.raw.background_music);
        imgamthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    imgamthanh.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    mediaPlayer.setLooping(true);
                }
                else{
                    mediaPlayer.pause();
                    imgamthanh.setImageResource(R.drawable.ic_volume_off_black_24dp);
                }
            }
        });
    }
    public void Trochoimoi(View view){
        new LinhVucAsyncTask(this).execute(URLl.url_get_linh_vuc);
    }
    public void Lichsuchoi(View view){
        Intent intent = new Intent(MainActivity.this,History.class);
        startActivity(intent);
    }
    public void Quanlitaikhoan(View view){
        Intent intent = new Intent(MainActivity.this,Infoaccount.class);
        startActivity(intent);
    }
    public void Bangxephang(View view){
        new NguoiChoiAsysTask(this).execute(URLl.url_xep_hang);

    }
    public void Muacredit(View view){
        new ShopcreditAsynctask(this).execute(URLl.url_goi_credit);
       // Intent intent = new Intent(MainActivity.this,shopcredit.class);
       // startActivity(intent);
    }
}
