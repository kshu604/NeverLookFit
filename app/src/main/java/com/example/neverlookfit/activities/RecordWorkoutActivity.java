package com.example.neverlookfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.neverlookfit.R;

public class RecordWorkoutActivity extends AppCompatActivity {

    private Button buttonPlateCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_workout);
        initButtons();
    }

    private void initButtons() {
        buttonPlateCalculator = (Button) findViewById(R.id.buttonPlateCalculator);
        buttonPlateCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlateCalculatorActivity.class);
                startActivity(intent);
            }
        });
    }


}
