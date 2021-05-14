package com.example.mvvmboradtest.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.example.mvvmboradtest.activities.MainActivity;

public class MessageDialog {

    public void ShowMessageDialog(Context context , String title_msg, String _msg, MessageInterface messageInterface, boolean _delete) {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(context);
        msgBuilder.setTitle(title_msg);
        msgBuilder.setMessage(_msg);
        if (_delete) {
            msgBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    messageInterface.DeleteYes();
                    Log.e("delete" , "eeeweqweqweqweqweqweqweq");
                }
            });
            msgBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    messageInterface.DeleteNo();
                }
            });
        } else {
            msgBuilder.setNegativeButton("confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    messageInterface.ErrorConfirm();
                }
            });
        }

        AlertDialog delete_dialog = msgBuilder.create();
        delete_dialog.show();
    }

}
