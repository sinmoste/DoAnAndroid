package com.example.ailatrieuphu.Class;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CustomSharedpreferences {
    public Context context;

    public CustomSharedpreferences( Context context) {
        this.context = context;
    }

    public void addShared(SharedPreferences mShared, String mSharedName, Map<String, String> mMap){
        mShared = context.getSharedPreferences(mSharedName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mShared.edit();
        for(String key:mMap.keySet()){
            editor.putString(key,mMap.get(key));
        }
        editor.commit();
    }
    public String getShared(SharedPreferences mShared, String mSharedName,String key){
        mShared = context.getSharedPreferences(mSharedName,Context.MODE_PRIVATE);
        String a = mShared.getString(key,null);
        return a;
    }




}
