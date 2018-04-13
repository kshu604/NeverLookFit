package com.example.neverlookfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;

import com.example.neverlookfit.R;

public class HomePageActivity extends AppCompatActivity {

    private final AppCompatActivity activity = HomePageActivity.this;
    private AppCompatTextView textViewName;
    private Button ProgressPhotos, RecordWorkout, SetGoals, ViewGoals;
    private static final int REQUEST_WORKOUT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
        initButtons();
    }

    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
    }

    private void initObjects() {
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
    }

    private void initButtons() {
        ProgressPhotos = (Button) findViewById(R.id.buttonProgressPhotos);
        ProgressPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgressPhotoActivity.class);
                startActivity(intent);
            }
        });
        RecordWorkout = (Button) findViewById(R.id.buttonRecordWorkout);
        RecordWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordWorkoutDateActivity.class);
                startActivityForResult(intent, REQUEST_WORKOUT_CODE);
            }
        });
        SetGoals = (Button) findViewById(R.id.buttonSetGoals);
        SetGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetGoalsActivity.class);
                startActivity(intent);
            }
        });
        ViewGoals = (Button) findViewById(R.id.buttonViewGoals);
        ViewGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewGoalsActivity.class);
                startActivity(intent);
            }
        });
    }


}
