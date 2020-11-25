package com.csis_3175_070.birthdayreminderapp;

import android.content.DialogInterface;
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

public class UpdateActivity extends AppCompatActivity {

    EditText firstName, lastName, dateOfBirth;
    Button updateButton, deleteButton;
    CheckBox notification;

    String id, fName, lName, dob;
    int noti;

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
                }else {
                    noti = 0;
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
