package com.example.ailatrieuphu.Class;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Forgetpassword;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.ReadAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QuenMatKhauAsynctask extends AsyncTask<String,String,String> {
    Context context;
    AlertDialog mDialog;
    public QuenMatKhauAsynctask(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       mDialog = new AlertDialog.Builder(context)
                                .setMessage("Đang xử lý vui lòng đợi trong giây lát")
                                .setIcon(R.drawable.circle_progressbar)
                                .show();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection=null;
        StringBuilder stringBuilder=new StringBuilder();
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(strings[0]);
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream inputStream=urlConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line).append("\n");
            }
            if (stringBuilder.length()==0){
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mDialog.dismiss();
        if(!s.equals("0\n")){
            new CustomDialog(context).showDialog("Thông báo","Thông tin đã được gửi tới email của bạn! \n Vui lòng kiểm tra lại email!");
        }else{
            new CustomDialog(context).showDialog("Thông báo","Tài khoản hoặc email không tồn tại");
        }
    }
}
