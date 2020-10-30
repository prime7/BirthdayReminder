package com.csis_3175_070.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class BirthdayDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_detail);

        int birthdayId = getIntent().getIntExtra("BIRTHDAY_ID",0);
        Log.d("BirthdayDetailActivity", "onCreate: "+birthdayId);
    }
}