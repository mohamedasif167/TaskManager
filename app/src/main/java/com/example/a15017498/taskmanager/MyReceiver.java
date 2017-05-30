package com.example.a15017498.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    int reqCode = 12345;


    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        String id = intent.getStringExtra("ID");

        Intent i = new Intent (context,MainActivity.class);
        Intent add = new Intent (context,addActivity.class);
        Intent delete = new Intent(context,deleteReceiver.class);
        delete.putExtra("ID",id);


        PendingIntent pIntent = PendingIntent.getActivity(context,reqCode,i, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent aIntent = PendingIntent.getActivity(context,reqCode,add,PendingIntent.FLAG_CANCEL_CURRENT);

        PendingIntent DIntent = PendingIntent.getActivity(context,reqCode,delete,PendingIntent.FLAG_CANCEL_CURRENT);

        //Build Notification
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(name);
        builder.setContentText(desc);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        // This 1 is, when the user clicks on the notification
        builder.setContentIntent(pIntent);
        //To cancel
        builder.setAutoCancel(true);
        // Adding Action Buttons into the notification
        builder.addAction(android.R.drawable.ic_input_add, "Add New Task", aIntent);
        builder.addAction(android.R.drawable.ic_input_delete, "Delete the Task", DIntent);

        Notification n = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123,n);
    }


}
