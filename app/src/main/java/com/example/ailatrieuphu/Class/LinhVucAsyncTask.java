package com.example.ailatrieuphu.Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ailatrieuphu.Question;
import com.example.ailatrieuphu.ReadAPI;

public class LinhVucAsyncTask extends AsyncTask<String,String,String> {
    Context context;

    public LinhVucAsyncTask(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
    }



    @Override
    protected void onPostExecute(String s) {
        Intent mIntent = new Intent(context, Question.class);
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
