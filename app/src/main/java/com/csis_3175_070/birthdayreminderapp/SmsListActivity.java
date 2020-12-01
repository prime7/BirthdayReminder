package com.csis_3175_070.birthdayreminderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class SmsListActivity extends Activity {

    ListView simpleList;
    String smsTitles[] = {
        "Wish for sister",
        "Wish for brother",
        "Wish for mother",
        "Wish for father",
        "Wish for friend",
    };
    String smsDetails[] = {
        "My wonderful sister, you are only one in this entire world and I couldn’t find another great sister like you anywhere. Happy Birthday.",
        "Happy Birthday Brother. Pop some champagne and let’s you toast to you! Love you, brother!",
        "Happy birthday, Mom! I want you to know that I am nothing without you, but I can be everything with you by my side. Love you!",
        "Laughter and love are the best things that you have given to me, dad! Thank you for that and I wish you a happy birthday!",
        "Best friend: someone whom you can be yourself with, someone who you can have pointless conversations with, someone who still likes you even when you’re weird, someone who forgets to buy you a birthday gift…that’s why I came up with this. Happy birthday my best friend!",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);

        simpleList = findViewById(R.id.smsListView);
        CustomSMSAdapter customSMSAdapter = new CustomSMSAdapter(getApplicationContext(), smsTitles);
        simpleList.setAdapter(customSMSAdapter);

        simpleList.setOnItemClickListener((adapterView, view, pos, l) -> {
            Intent intent = new Intent(SmsListActivity.this, SmsDetailActivity.class);
            intent.putExtra("description",smsDetails[pos]);
            startActivity(intent);
        });
    }
}