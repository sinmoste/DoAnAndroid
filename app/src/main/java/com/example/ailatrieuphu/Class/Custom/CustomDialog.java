package com.example.ailatrieuphu.Class.Custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.ailatrieuphu.Class.Countdown;
import com.example.ailatrieuphu.Class.URLl;
import com.example.ailatrieuphu.ReadAPI;

import java.util.HashMap;
import java.util.Map;

public class CustomDialog {
    public CustomDialog(Context context) {
        this.context = context;
    }

    public Context context;

    public void showDialogandPostAPI(String title, String message, final Map<String,String> mMap, final String duongdan){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Them luot choi
                        ReadAPI.PostAPI(context,  mMap, duongdan);
                        ((Activity) context).finish();
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                //.setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
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
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
