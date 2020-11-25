package com.csis_3175_070.birthdayreminderapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BirthdayListActivity extends AppCompatActivity {
    static final int ADD_BIRTHDAY_REQUEST = 1;

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ImageView emptyImageView;
    TextView noData;

    DBHelper myDB;
    ArrayList<String> bdayId;
    ArrayList<String> bdayFname;
    ArrayList<String> bdayLname;
    ArrayList<Integer> notification;
    ArrayList<String> bdayDate;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_list);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.add_button);
        emptyImageView = findViewById(R.id.empty_imageview);
        noData = findViewById(R.id.no_data);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BirthdayListActivity.this, DOBAddActivity.class);
                startActivityForResult(intent, ADD_BIRTHDAY_REQUEST);
            }
        });

        myDB = new DBHelper(BirthdayListActivity.this);
        bdayId = new ArrayList<>();
        bdayFname = new ArrayList<>();
        bdayLname = new ArrayList<>();
        notification = new ArrayList<Integer>();
        bdayDate = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(BirthdayListActivity.this, this, bdayId, bdayFname, bdayLname, notification, bdayDate);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BirthdayListActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            emptyImageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                bdayId.add(cursor.getString(0));
                bdayFname.add(cursor.getString(1));
                bdayLname.add(cursor.getString(2));
                notification.add(cursor.getInt(3));
                bdayDate.add(cursor.getString(4));
            }
            emptyImageView.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(BirthdayListActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(BirthdayListActivity.this, BirthdayListActivity.class);
                startActivity(intent);
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
