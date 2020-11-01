package com.csis_3175_070.birthdayreminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class BirthdayListActivity extends ListActivity {
    public static String[] allBirthdayList = {
            "Add Birthday Input",
            "14 December - Ayon Elahi",
            "01 March - Mir Alahi",
            "30 January - John Smith",
            "19 July - John Snow",
            "14 December - Robert Downing",
            "01 March - Will Smith",
            "30 January - Cristiano Ronaldo",
            "19 July - Lionel Messi",
            "14 December - Khabib Normagomedov",
            "01 March - Conor Mcgregor",
            "30 January- Justin Gaethji",
            "19 July - Donald Trump",
            "14 December - Barack Obama",
            "01 March - Justin Trudeau",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                finish();
//                startActivity(new Intent(BirthdayListActivity.this, BirthdayDetailActivity.class)); //
//            }
//        };
//        Timer opening = new Timer();
//        opening.schedule(task,3000);

        setListAdapter(new ArrayAdapter<String>(
                this,
                R.layout.activity_birthday_list,
                R.id.birthdayList,
                allBirthdayList
        ));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position==0){
            startActivity(new Intent(BirthdayListActivity.this,DOBAddActivity.class));
        }
        else {
            Intent intent = new Intent(this, BirthdayDetailActivity.class);
            intent.putExtra("BIRTHDAY_ID", position);
            startActivity(intent);
        }
    }
}