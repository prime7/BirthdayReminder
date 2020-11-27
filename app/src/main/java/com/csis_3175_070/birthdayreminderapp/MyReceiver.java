package com.csis_3175_070.birthdayreminderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase("com.tarek.alarm")){
            Bundle b = intent.getExtras();
//            Toast.makeText(context, b.getString("Message"), Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setClass(context, ReminderNotification.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Message",b.getString("Message"));
            context.startActivity(i);
        }
    }
}