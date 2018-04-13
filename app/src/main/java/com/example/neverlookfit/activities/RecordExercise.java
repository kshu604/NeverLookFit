package com.example.neverlookfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.neverlookfit.R;

public class RecordExercise extends AppCompatActivity {

    private TextView editTextExercise,
            editTextWeight1, editTextWeight2, editTextWeight3, editTextWeight4,
            editTextReps1, editTextReps2, editTextReps3, editTextReps4;

    public Bundle exercise = new Bundle();
    public Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_exercise);

        editTextExercise = findViewById(R.id.editTextExercise);
        editTextWeight1 = findViewById(R.id.editTextWeight1);
        editTextWeight2 = findViewById(R.id.editTextWeight2);
        editTextWeight3 = findViewById(R.id.editTextWeight3);
        editTextWeight4 = findViewById(R.id.editTextWeight4);

        editTextReps1 = findViewById(R.id.editTextReps1);
        editTextReps2 = findViewById(R.id.editTextReps2);
        editTextReps3 = findViewById(R.id.editTextReps3);
        editTextReps4 = findViewById(R.id.editTextReps4);

    }

    private void initButtons() {
        Button buttonRecord = (Button) findViewById(R.id.buttonRecord);
        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.putString("Exercise", editTextExercise.getText().toString());

                exercise.putString("Weight1", editTextWeight1.getText().toString());
                exercise.putString("Weight2", editTextWeight2.getText().toString());
                exercise.putString("Weight3", editTextWeight3.getText().toString());
                exercise.putString("Weight4", editTextWeight4.getText().toString());

                exercise.putString("Reps1", editTextReps1.getText().toString());
                exercise.putString("Reps2", editTextReps2.getText().toString());
                exercise.putString("Reps3", editTextReps3.getText().toString());
                exercise.putString("Reps4", editTextReps4.getText().toString());

                intent.putExtras(exercise);
                setResult(RecordWorkoutActivity.RESULT_OK, intent);
                finish();
            }
        });

        Button buttonPlateCalculator = (Button) findViewById(R.id.buttonPlateCalculator);
        buttonPlateCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlateCalculatorActivity.class);
                startActivity(intent);
            }
        });

        Button buttonStopwatch = (Button) findViewById(R.id.buttonStopwatch);
        buttonStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StopwatchIntent = new Intent(AlarmClock.ACTION_SET_TIMER);
                startActivity(StopwatchIntent);
            }
        });

    }
}