package com.example.ailatrieuphu;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadJson {

    public static ArrayList<CauHoiItem> readJSONFile(Context context) throws IOException, JSONException {


        ArrayList<CauHoiItem> list = new ArrayList<>();

        String jsonText = readText(context, R.raw.cauhoi);

        JSONObject jsonObject = new JSONObject(jsonText);

        JSONArray jsonArray = jsonObject.getJSONArray("cauHoi");
        try {
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject cit = jsonArray.getJSONObject(j);
                Integer id = cit.getInt("ID");
                String noidung = cit.getString("Noidung");
                String phuongana = cit.getString("PhuonganA");
                String phuonganb = cit.getString("PhuonganB");
                String phuonganc = cit.getString("PhuonganC");
                String phuongand = cit.getString("PhuonganD");

                list.add(new CauHoiItem(id, noidung, phuongana, phuonganb, phuonganc, phuongand));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String readText(Context context, int resId) throws IOException {

        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while(( s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
