package com.example.ailatrieuphu.Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ailatrieuphu.Question;
import com.example.ailatrieuphu.Rank;
import com.example.ailatrieuphu.ReadAPI;

public class NguoiChoiAsysTask extends AsyncTask<String,String,String> {
    Context context;

    public NguoiChoiAsysTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
    }
    @Override
    protected void onPostExecute(String s) {
        Intent mIntent = new Intent(context, Rank.class);
        mIntent.putExtra("NguoiChoi",s);
        Activity activity = (Activity) context;
        activity.startActivity(mIntent);
       // Toast.makeText(context,s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

    }
}
