package com.example.ailatrieuphu.Class.Custom;

import android.app.Activity;
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
                .setIcon(R.drawable.ic_favorite_heart_24dp)
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
                .setIcon(R.drawable.ic_favorite_heart_24dp)
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
    public void randomm(int[] a,String b){
        switch (b){
            case "A":
                a[0]= new Random().nextInt(15)+10;
                a[1]= new Random().nextInt(15)+5;
                a[2]= new Random().nextInt(15)+5;
                a[3]= new Random().nextInt(15)+5;
                break;
            case "B":
                a[0]= new Random().nextInt(15)+5;
                a[1]= new Random().nextInt(15)+10;
                a[2]= new Random().nextInt(15)+5;
                a[3]= new Random().nextInt(15)+5;
                break;
            case "C":
                a[0]= new Random().nextInt(15)+5;
                a[1]= new Random().nextInt(15)+5;
                a[2]= new Random().nextInt(15)+10;
                a[3]= new Random().nextInt(15)+5;
                break;
            case "D":
                a[0]= new Random().nextInt(15)+5;
                a[1]= new Random().nextInt(15)+5;
                a[2]= new Random().nextInt(15)+5;
                a[3]= new Random().nextInt(15)+10;
                break;
        }
        int sum=0;
        do{
            sum=a[0]+a[1]+a[2]+a[3];
            if(sum==100){break;}
            a[0]++;
            a[1]++;
            a[2]++;
            a[3]++;
            System.out.println("A" + a[0]);
            System.out.println("B" +a[1]);
            System.out.println("C" +a[2]);
            System.out.println("D" +a[3]+"\n");
        }
        while(sum<100);
    }
    public void showResultDialog(String title,String message){
        new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Activity ac = (Activity)context;
                        ac.finish();
                    }
                }).setIcon(R.drawable.archivement).show();
    }
    public void showDialogBuy(){

    }

    public void showDialogaudience(String b){
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_khangia);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        BarChart barChart = dialog.findViewById(R.id.barchart);
        int[] a = new int[4];
        randomm(a,b);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(a[0], 0));
        entries.add(new BarEntry(a[1], 1));
        entries.add(new BarEntry(a[2], 2));
        entries.add(new BarEntry(a[3], 3));
        BarDataSet bardataset = new BarDataSet(entries, "A B C D");
        ArrayList<String> labels = new ArrayList<>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        BarData data = new BarData(labels,bardataset);
        barChart.setData(data);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(2000);
        Button btnendkhangia = dialog.findViewById(R.id.btnendkhangia);
        btnendkhangia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
