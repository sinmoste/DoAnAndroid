package com.example.ailatrieuphu.Class.Custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

import com.example.ailatrieuphu.Class.ChiTietLuotChoi;
import com.example.ailatrieuphu.Class.URLl;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.ReadAPI;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class CustomDialog {
    public CustomDialog(Context context) {
        this.context = context;
    }

    public Context context;

    public void showDialogandPostAPI(String title, String message, final Map<String,String> mMap, final String duongdan){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ReadAPI.PostAPI(context,mMap, URLl.url_them_luot_choi);
                    }
                })
                //.setNegativeButton(android.R.string.no, null)
                .show();
    }
    public void showDialogandPostAPI(String title, String message, final Map<String,String> mMap, final String duongdan,final ArrayList<ChiTietLuotChoi> mArray){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ReadAPI.PostAPI(context,mMap, URLl.url_them_luot_choi,mArray);
                    }
                })
                //.setNegativeButton(android.R.string.no, null)
                .show();
    }//  ReadAPI.PostAPI(context,mMap, URLl.url_them_luot_choi,mArray);

    public void showDialog(String title,String message){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                //.setNegativeButton(android.R.string.no, null)
                .show();
    }

    public void showDialogaudience(){
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_khangia);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        BarChart barChart = dialog.findViewById(R.id.barchart);
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int c = random.nextInt(100);
        int d = random.nextInt(100);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(a, 0));
        entries.add(new BarEntry(b, 1));
        entries.add(new BarEntry(c, 2));
        entries.add(new BarEntry(d, 3));
        BarDataSet bardataset = new BarDataSet(entries, "Cells");
        ArrayList<String> labels = new ArrayList<>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        BarData data = new BarData(labels,bardataset);
        barChart.setData(data);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(1000);
        Button btnendkhangia = dialog.findViewById(R.id.btnendkhangia);
        btnendkhangia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
