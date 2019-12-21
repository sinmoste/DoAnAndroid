package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        Intent intent = new Intent(MainActivity.this,Question.class);
        startActivity(intent);
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
        Intent intent = new Intent(MainActivity.this,Rank.class);
        startActivity(intent);
    }
    public void Muacredit(View view){
        Intent intent = new Intent(MainActivity.this,Credit.class);
        startActivity(intent);
    }

}
