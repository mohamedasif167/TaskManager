package com.example.a15017498.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addActivity extends AppCompatActivity {

    Button btnAdd,btnCancel;
    EditText edtName,edtDesc,edtTime;
    int reqCode = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        edtName = (EditText) findViewById(R.id.edtName);
        edtDesc = (EditText) findViewById(R.id.edtDesc);
        edtTime = (EditText) findViewById(R.id.edtTime);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String desc = edtDesc.getText().toString();
                String time = edtTime.getText().toString();
                int Time = Integer.parseInt(time);

                DBHelper dbh = new DBHelper(addActivity.this);
                long adding = dbh.insertNote(name,desc);
                dbh.close();

                if (adding != -1){
                    Toast.makeText(addActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,Time);

                //Task task = new Task(name,desc);

                Intent intent = new Intent(addActivity.this,MyReceiver.class);
                intent.putExtra("name",name);
                intent.putExtra("desc",desc);
                int fakeID = Integer.parseInt(String.valueOf(adding));
                int total = fakeID + 2;
                intent.putExtra("ID",total+"");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(addActivity.this,reqCode,intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);


                Intent i = new Intent();
                i.putExtra("grade", 9);
                // Set result to RESULT_OK to indicate normal response and pass in the intent containing the data
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
