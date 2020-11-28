package com.csis_3175_070.birthdayreminderapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    EditText firstName, lastName, dateOfBirth;
    Button updateButton, deleteButton;
    CheckBox notification;

    String id, fName, lName, dob;
    int noti;
    int notId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        firstName = findViewById(R.id.ETFirstName);
        lastName = findViewById(R.id.ETLastName);
        notification = findViewById(R.id.checkBoxNotification);
        dateOfBirth = findViewById(R.id.ETDateOfBirth);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(fName);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //And only then we call this
                DBHelper myDB = new DBHelper(UpdateActivity.this);
                fName = firstName.getText().toString().trim();
                lName = lastName.getText().toString().trim();
                if (notification.isChecked()){
                    noti = 1;

                    dob = dateOfBirth.getText().toString().trim();
                    String[] d = dob.split("-");
                    int day = Integer.parseInt(d[0]);
                    int month = Integer.parseInt(d[1]);

                    Log.d("ASD",day+" "+month);

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY,22);
                    calendar.set(Calendar.MINUTE,04);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.DAY_OF_MONTH,day);
                    calendar.set(Calendar.MONTH,month);

                    AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(getBaseContext(),MyReceiver.class);
                    intent.setAction("com.tarek.alarm");
                    intent.putExtra("Message","It's "+fName+" "+lName+"'s Birthday tomorrow.");

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

                }else {
                    noti = 0;
                    AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(getBaseContext(),MyReceiver.class);
                    intent.setAction("com.tarek.alarm");
                    intent.putExtra("Message","It's "+fName+" "+lName+"'s Birthday tomorrow.");

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                    manager.cancel(pendingIntent);
                }
                dob = dateOfBirth.getText().toString().trim();
                myDB.updateData(id, fName, lName, noti,dob);
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("firstname") &&
                getIntent().hasExtra("lastname") && getIntent().hasExtra("date")){

            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            fName = getIntent().getStringExtra("firstname");
            lName = getIntent().getStringExtra("lastname");
            noti = getIntent().getIntExtra("notification",0);
            dob = getIntent().getStringExtra("date");

            //Setting Intent Data
            firstName.setText(fName);
            lastName.setText(lName);
            if(noti==0){
                notification.setChecked(false);
            }else{
                notification.setChecked(true);
            }
            dateOfBirth.setText(dob);
            Log.d("stev", fName+" "+lName+" "+dob);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + fName + " ?");
        builder.setMessage("Are you sure you want to delete " + fName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(com.csis_3175_070.birthdayreminderapp.UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
