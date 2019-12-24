package com.example.ailatrieuphu.Class;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


public class Countdown extends AsyncTask<String,String,String> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private int percent;
    private WeakReference<ProgressBar> mPg;
    private WeakReference<TextView> mT;

    private int mTime;
    public Countdown(Context context, ProgressBar progressBarg,TextView mTt, int milisecond){
        percent=100;
        mPg = new WeakReference<>(progressBarg);
        mT = new WeakReference<>(mTt);
        mTime = milisecond;
        this.context = context;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(String s) {

        String nguoi_choi_id= new CustomSharedpreferences(this.context).getShared("LuotChoi","nguoi_choi_id");
        String so_cau = new CustomSharedpreferences(this.context).getShared("LuotChoi","so_cau");
        String diem = new CustomSharedpreferences(this.context).getShared("LuotChoi","diem");
        Map<String,String> mMap = new HashMap<>();
        mMap.put("nguoi_choi_id",nguoi_choi_id);
        mMap.put("so_cau",so_cau);
        mMap.put("diem",diem);
        new CustomDialog(this.context).showDialogandPostAPI(
               "Hết thời gian",
               "Điểm của bạn là: "+ diem + "\n" + "Số câu đúng: " + so_cau,
                mMap,
                URLl.url_them_luot_choi);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onProgressUpdate(String... values) {
        mPg.get().setProgress(percent);
        int a = (int) (percent/3.3);
        mT.get().setText(Integer.toString(a));
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            while(percent!=0){
                percent--;
                Thread.sleep(mTime);
                publishProgress(Integer.toString(percent));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.toString(percent);
    }
}
