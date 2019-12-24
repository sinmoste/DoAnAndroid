package com.example.ailatrieuphu.Class.Custom;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Map;

public class CustomSharedpreferences {
    public Context context;

    public CustomSharedpreferences( Context context) {
        this.context = context;
    }

    public void addShared(String mSharedName, Map<String, String> mMap){
        SharedPreferences mShared = context.getSharedPreferences(mSharedName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mShared.edit();
        for(String key:mMap.keySet()){
            editor.putString(key,mMap.get(key));
        }
        editor.commit();
    }
    public String getShared( String mSharedName,String key){
        SharedPreferences mShared = context.getSharedPreferences(mSharedName,Context.MODE_PRIVATE);
        String a = mShared.getString(key,null);
        return a;
    }
    public void deleteShared(String sharedname){
        SharedPreferences mShared = context.getSharedPreferences(sharedname,0);
        mShared.edit().clear().commit();
    }




}
