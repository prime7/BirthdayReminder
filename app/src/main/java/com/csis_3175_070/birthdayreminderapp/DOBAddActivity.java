package com.csis_3175_070.birthdayreminderapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DOBAddActivity extends AppCompatActivity {

    EditText fName, lName, date;
    Button addButton;
    DBHelper DB;

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
                if(FirstN.isEmpty() || LastN.isEmpty() || Date.isEmpty()){
                    Toast.makeText(getBaseContext(),"Please fill up all the information properly",Toast.LENGTH_LONG).show();
                }
                else{
                    DBHelper myDB = new DBHelper(DOBAddActivity.this);
                    myDB.addDob(FirstN, LastN, Date);
                    finish();
                }
            }
        });

        date.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
                date.setText(simpleDateFormat.format(calendar.getTime()));
            };
            new DatePickerDialog(DOBAddActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }
}

