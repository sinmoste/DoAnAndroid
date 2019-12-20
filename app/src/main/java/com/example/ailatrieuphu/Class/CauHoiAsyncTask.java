package com.example.ailatrieuphu.Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.ailatrieuphu.Answerquestion;
import com.example.ailatrieuphu.ReadAPI;

public class CauHoiAsyncTask extends AsyncTask<String,String,String> {
        Context context;

        public CauHoiAsyncTask(Context context){
        this.context = context;
        }
@Override
protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
        }



@Override
protected void onPostExecute(String s) {
        Intent mIntent = new Intent(context, Answerquestion.class);
        mIntent.putExtra("message",s);
        Activity activity = (Activity) context;
        activity.startActivity(mIntent);
        //Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
        }

@Override
protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        }
}
