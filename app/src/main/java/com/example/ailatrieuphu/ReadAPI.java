package com.example.ailatrieuphu;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Class.ChiTietLuotChoi;
import com.example.ailatrieuphu.Class.Custom.CustomSharedpreferences;
import com.example.ailatrieuphu.Class.URLl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadAPI {
    public static String GetJSON(String duongdan){
        HttpURLConnection urlConnection=null;
        StringBuilder stringBuilder=new StringBuilder();
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(duongdan);
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream inputStream=urlConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line);
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

    public static void PostAPI(final Context context, final Map<String,String> mMap, String duongdan)
    {
       StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                try {
//                    JSONArray jr = new JSONArray(response);
//                    JSONObject jb = jr.getJSONObject(5);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
//                Map<String,String> params=mMap;
//                for(String key:mMap.keySet()){
//                    params.put( key,mMap.get(key));
//                }
                return mMap;

            }
        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        requestQueue.add(stringRequest);


    }


    //Them chi tiet luot choi
    public static void PostAPI(final Context context, final Map<String,String> mMap, String duongdan, final ArrayList<ChiTietLuotChoi> mArray)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=1;
                try {
                    JSONObject jb = new JSONObject(response);
                    id = jb.getInt("id");
                    int nguoi_choi_id = jb.getInt("nguoi_choi_id");
                    int diem_cao_nhat = jb.getInt("diem");
                    Map<String,String > map3 = new HashMap<>();//Cap nhap diem co cao ko
                    map3.put("nguoi_choi_id", String.valueOf(nguoi_choi_id));
                    map3.put("diem",String.valueOf(diem_cao_nhat));
                    new CustomSharedpreferences(context).addShared("LuotChoi",map3);
                    PostAPI(context,map3,URLl.url_cap_nhat_diem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    for(int i=0;i<mArray.size();i++) {
                        String cau_hoi_id = String.valueOf(mArray.get(i).getCau_hoi_id());
                        String phuong_an = mArray.get(i).getPhuong_an();
                        String diem = String.valueOf(mArray.get(i).getDiem());
                        Map<String,String> map2 = new HashMap<>();
                        map2.put("luot_choi_id", String.valueOf(id));
                        map2.put("cau_hoi_id",cau_hoi_id);
                        map2.put("phuong_an",phuong_an);
                        map2.put("diem",diem);
                        PostAPI(context,map2, URLl.url_them_chi_tiet);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
//                Map<String,String> params=mMap;
//                for(String key:mMap.keySet()){
//                    params.put( key,mMap.get(key));
//                }
                return mMap;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static String encodeBitmapToString(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}




