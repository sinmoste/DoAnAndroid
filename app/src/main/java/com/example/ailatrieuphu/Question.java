package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ailatrieuphu.Class.Adapter.LinhVucAdapter;
import com.example.ailatrieuphu.Class.LinhVuc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Question extends AppCompatActivity {
    RecyclerView mRecyclerview;
    ArrayList<LinhVuc> mLinhVuc;
    LinhVucAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent mIntent = getIntent();
        String jsonString = mIntent.getStringExtra("message");

        if(getJson(jsonString)){

        }else{
            Toast.makeText(this,"Get API linh vuc failed",Toast.LENGTH_SHORT).show();
        }

        mRecyclerview = findViewById(R.id.rcl_linhvuc);
        mAdapter = new LinhVucAdapter(Question.this,mLinhVuc);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }


    public boolean getJson(String jsonString){
        try{
            mLinhVuc =  new ArrayList<>();
            JSONObject data = new JSONObject(jsonString);
            JSONArray jr = data.getJSONArray("data");

            int length = jr.length();
            for(int i =0;i<length;i++){
            JSONObject jb = jr.getJSONObject(i);
                mLinhVuc.add(new LinhVuc(jb.getInt("id"),jb.getString("ten"),jb.getInt("xoa")));
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
