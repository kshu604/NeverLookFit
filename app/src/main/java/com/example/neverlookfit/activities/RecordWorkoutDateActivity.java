package com.example.neverlookfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.neverlookfit.R;

public class RecordWorkoutDateActivity extends AppCompatActivity implements View.OnClickListener {

    String date;
    private Button buttonMonday, buttonTuesday, buttonWednesday, buttonThursday, buttonFriday, buttonSaturday, buttonSunday;
    private static final int REQUEST_WORKOUT_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_workout_date);
        initButtons();
        initListeners();
    }

    private void initButtons() {
        buttonMonday = (Button) findViewById(R.id.buttonMonday);
        buttonTuesday = (Button) findViewById(R.id.buttonTuesday);
        buttonWednesday = (Button) findViewById(R.id.buttonWednesday);
        buttonThursday = (Button) findViewById(R.id.buttonThursday);
        buttonFriday = (Button) findViewById(R.id.buttonFriday);
        buttonSaturday = (Button) findViewById(R.id.buttonSaturday);
        buttonSunday = (Button) findViewById(R.id.buttonSunday);

    }

    private void initListeners() {
        buttonMonday.setOnClickListener(this);
        buttonTuesday.setOnClickListener(this);
        buttonWednesday.setOnClickListener(this);
        buttonThursday.setOnClickListener(this);
        buttonFriday.setOnClickListener(this);
        buttonSaturday.setOnClickListener(this);
        buttonSunday.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonMonday:
                date = "Monday";
                recordActivity();
                break;
            case R.id.buttonTuesday:
                date = "Tuesday";
                recordActivity();
                break;
            case R.id.buttonWednesday:
                date = "Wednesday";
                recordActivity();
                break;
            case R.id.buttonThursday:
                date = "Thursday";
                recordActivity();
                break;
            case R.id.buttonFriday:
                date = "Friday";
                recordActivity();
                break;
            case R.id.buttonSaturday:
                date = "Saturday";
                recordActivity();
                break;
            case R.id.buttonSunday:
                date = "Sunday";
                recordActivity();
                break;
        }
    }

    private void recordActivity() {
        Intent intent = new Intent(this, RecordWorkoutActivity.class);
        intent.putExtra("day", date);
        startActivityForResult(intent, REQUEST_WORKOUT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_WORKOUT_CODE) {
            if (resultCode == RESULT_OK) {
                setResult(HomePageActivity.RESULT_OK, data);
                finish();
            }
        }
    }

}
