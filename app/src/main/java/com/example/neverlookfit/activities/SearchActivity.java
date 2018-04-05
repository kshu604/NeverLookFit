package com.example.neverlookfit.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.neverlookfit.R;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {
    private TextView minDisplayDate, maxDisplayDate;
    private String startDate, endDate;
    private EditText keywordDisplay, locationDisplay;

    private DatePickerDialog.OnDateSetListener minDateListener;
    private DatePickerDialog.OnDateSetListener maxDateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        minDisplayDate = (TextView) findViewById(R.id.minDate);
        maxDisplayDate = (TextView) findViewById(R.id.maxDate);
        keywordDisplay = (EditText) findViewById(R.id.search_keyword);
        locationDisplay = (EditText) findViewById(R.id.search_location);

        minDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SearchActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        minDateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        minDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //String date = month + "/" + day + "/" + year;
                startDate = String.format("%04d%02d%02d", year, month, day);
                minDisplayDate.setText(startDate);
            }
        };

        maxDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SearchActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        maxDateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        maxDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //String date = month + "/" + day + "/" + year;
                //Pattern pattern = Pattern.compile("_([0-9]*?)_");
                endDate = String.format("%04d%02d%02d", year, month, day);
                maxDisplayDate.setText(endDate);
            }
        };
    }

    public void search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("STARTDATE", minDisplayDate.getText().toString());
        intent.putExtra("ENDDATE", maxDisplayDate.getText().toString());
        intent.putExtra("KEYWORD", keywordDisplay.getText().toString());
        intent.putExtra("LOCATION", locationDisplay.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
