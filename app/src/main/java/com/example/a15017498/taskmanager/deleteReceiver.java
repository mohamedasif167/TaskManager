package com.example.a15017498.taskmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 15017498 on 30/5/2017.
 */

public class deleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String id = intent.getStringExtra("ID");
        int numID = Integer.parseInt(id);

        DBHelper dbh=new DBHelper(context);
        dbh.deleteTask(numID);

    }
}
