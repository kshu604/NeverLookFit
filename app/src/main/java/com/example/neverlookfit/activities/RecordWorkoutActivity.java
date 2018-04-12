package com.example.neverlookfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.neverlookfit.R;

public class RecordWorkoutActivity extends AppCompatActivity {

//    private Button buttonPlateCalculator;
//    private Button buttonAddExercise;
    private static final int REQUEST_EXERCISE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        String day = i.getStringExtra("day");

        setContentView(R.layout.activity_record_workout);
        initButtons();
    }

    private void initButtons() {
        Button buttonPlateCalculator = (Button) findViewById(R.id.buttonPlateCalculator);
        buttonPlateCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlateCalculator.class);
                startActivity(intent);
            }
        });

        Button buttonAddExercise = (Button) findViewById(R.id.buttonAddExercise);
        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExercise = new Intent(getApplicationContext(), RecordExercise.class);
                startActivityForResult(intentExercise, REQUEST_EXERCISE_CODE);
            }
        });

    }


}
