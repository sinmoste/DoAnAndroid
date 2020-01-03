package com.example.ailatrieuphu.Class;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.ailatrieuphu.Class.Custom.CustomDialog;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.example.ailatrieuphu.ReadAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThemCauHoiAsynctask extends AsyncTask< ArrayList<ChiTietLuotChoi>, String,String> {
    Context context;
    AlertDialog mDialog;
    public ThemCauHoiAsynctask(Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(ArrayList<ChiTietLuotChoi>... arrayLists) {
        Map<String,String> map = new HashMap<>();
        map.put("nguoi_choi_id",new CustomSharedpreferences(context).getShared("NguoiChoi","id"));
        map.put("so_cau",new CustomSharedpreferences(context).getShared("LuotChoi","so_cau"));
        map.put("diem",new CustomSharedpreferences(context).getShared("LuotChoi","diem"));
        ReadAPI.PostAPI(context,map,URLl.url_them_luot_choi,arrayLists[0]);
        return new CustomSharedpreferences(context).getShared("LuotChoi","diem");//return diem
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = new AlertDialog.Builder(context).setMessage("Đang xử lý ...").show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mDialog.dismiss();
        new CustomDialog(context).showResultDialog("Chúc mừng","Điểm của bạn là :" + s);
    }



}
