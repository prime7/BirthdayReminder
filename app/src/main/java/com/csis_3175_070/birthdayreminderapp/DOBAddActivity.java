package com.csis_3175_070.birthdayreminderapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DOBAddActivity extends AppCompatActivity {

    EditText fName, lName, date;
    Button addButton;
    DBHelper DB;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_o_b_add);

        fName = findViewById(R.id.txtFirstName);
        lName = findViewById(R.id.txtLastName);
        date = findViewById(R.id.date_time_input);
        date.setInputType(InputType.TYPE_NULL);
        addButton = findViewById(R.id.btn);

        DB = new DBHelper(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FirstN = fName.getText().toString();
                String LastN = lName.getText().toString();
                String Date = date.getText().toString();
                DBHelper myDB = new DBHelper(DOBAddActivity.this);
                myDB.addDob(FirstN, LastN, Date);
                finish();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(DOBAddActivity.this,
                        (view, year, monthOfYear, dayOfMonth) -> {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
                            date.setText(simpleDateFormat.format(calendar.getTime()));

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
}
