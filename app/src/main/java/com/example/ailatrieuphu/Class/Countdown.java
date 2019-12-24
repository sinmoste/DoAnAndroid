package com.example.ailatrieuphu.Class;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.ailatrieuphu.Answerquestion;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.example.ailatrieuphu.ReadAPI;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class Countdown extends AsyncTask<String,String,String> {
    Context context;
    int percent;
    WeakReference<ProgressBar> mPg;
    int mTime;
    SharedPreferences mShared;
    Countdown(Context context,ProgressBar progressBarg,int time){
        this.context = context;
        percent=0;
        mPg = new WeakReference<>(progressBarg);
        this.mTime = time;
    }

    @Override
    protected void onPostExecute(String s) {
        String nguoi_choi_id= new CustomSharedpreferences(context).getShared("LuotChoit","nguoi_choi_id");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        mPg.get().setProgress(percent);
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            while(percent<mTime){
                percent++;
                Thread.sleep(1000);
                publishProgress(Integer.toString(percent));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.toString(percent);
    }
}
