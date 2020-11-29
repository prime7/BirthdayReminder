package com.csis_3175_070.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class ReminderNotification extends AppCompatActivity {
//    MP3 Downloaded from = https://www.zedge.net/ringtone/c0c8b6a7-78e2-33ee-8970-3d98c38a162b
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_notification);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");
        title = findViewById(R.id.textViewTitleReminder);
        title.setText(message);

        MediaPlayer mPlayer = MediaPlayer.create(ReminderNotification.this, R.raw.happy_birthday_tone);
        mPlayer.start();
    }
}