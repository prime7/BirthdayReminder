package com.csis_3175_070.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReminderNotification extends AppCompatActivity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_notification);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");
        title = findViewById(R.id.textViewTitleReminder);
        title.setText(message);
    }
}