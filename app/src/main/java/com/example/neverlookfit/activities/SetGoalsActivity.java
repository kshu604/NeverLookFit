package com.example.neverlookfit.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.neverlookfit.R;
import com.example.neverlookfit.model.Goal;
import com.example.neverlookfit.sql.DatabaseHelper;

public class SetGoalsActivity extends AppCompatActivity {

    private final AppCompatActivity activity = SetGoalsActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutDate;
    private TextInputLayout textInputLayoutWeight;
    private TextInputLayout textInputLayoutBodyFat;
    private TextInputLayout textInputLayoutThigh;
    private TextInputLayout textInputLayoutChest;
    private TextInputLayout textInputLayoutBiceps;
    private TextInputLayout textInputLayoutHip;
    private Button buttonRecord;

    private TextInputEditText textInputEditTextDate;
    private TextInputEditText textInputEditTextWeight;
    private TextInputEditText textInputEditTextBodyFat;
    private TextInputEditText textInputEditTextThigh;
    private TextInputEditText textInputEditTextChest;
    private TextInputEditText textInputEditTextBiceps;
    private TextInputEditText textInputEditTextHip;

    private Goal goal;
    private DatabaseHelper databaseHelperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goals);

        initViews();
        initObjects();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutDate = (TextInputLayout) findViewById(R.id.textInputLayoutDate);
        textInputLayoutWeight = (TextInputLayout) findViewById(R.id.textInputLayoutWeight);
        textInputLayoutBodyFat = (TextInputLayout) findViewById(R.id.textInputLayoutBodyFat);
        textInputLayoutThigh = (TextInputLayout) findViewById(R.id.textInputLayoutThigh);
        textInputLayoutChest = (TextInputLayout) findViewById(R.id.textInputLayoutChest);
        textInputLayoutBiceps = (TextInputLayout) findViewById(R.id.textInputLayoutBiceps);
        textInputLayoutHip = (TextInputLayout) findViewById(R.id.textInputLayoutHip);

        textInputEditTextDate = (TextInputEditText) findViewById(R.id.textInputEditTextDate);
        textInputEditTextWeight = (TextInputEditText) findViewById(R.id.textInputEditTextWeight);
        textInputEditTextBodyFat = (TextInputEditText) findViewById(R.id.textInputEditTextBodyFat);
        textInputEditTextThigh = (TextInputEditText) findViewById(R.id.textInputEditTextThigh);
        textInputEditTextChest = (TextInputEditText) findViewById(R.id.textInputEditTextChest);
        textInputEditTextBiceps = (TextInputEditText) findViewById(R.id.textInputEditTextBiceps);
        textInputEditTextHip = (TextInputEditText) findViewById(R.id.textInputEditTextHip);

        buttonRecord = (Button) findViewById(R.id.button_record);
        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });

    }

    private void initObjects() {
        databaseHelperLogin = new DatabaseHelper(activity);
        goal = new Goal();
    }

    private void postDataToSQLite() {
        goal.setDate(textInputEditTextDate.getText().toString().trim());
        goal.setWeight(Double.parseDouble(textInputEditTextWeight.getText().toString().trim()));
        goal.setBodyFat(Double.parseDouble(textInputEditTextBodyFat.getText().toString().trim()));
        goal.setThigh(Double.parseDouble(textInputEditTextThigh.getText().toString().trim()));
        goal.setChest(Double.parseDouble(textInputEditTextChest.getText().toString().trim()));
        goal.setBicep(Double.parseDouble(textInputEditTextBiceps.getText().toString().trim()));
        goal.setHip(Double.parseDouble(textInputEditTextHip.getText().toString().trim()));

        databaseHelperLogin.addGoal(goal);

        Snackbar.make(nestedScrollView, getString(R.string.success_goal), Snackbar.LENGTH_LONG).show();
        emptyInputEditText();
    }

    private void emptyInputEditText() {
        textInputEditTextDate.setText(null);
        textInputEditTextWeight.setText(null);
        textInputEditTextBodyFat.setText(null);
        textInputEditTextThigh.setText(null);
        textInputEditTextChest.setText(null);
        textInputEditTextBiceps.setText(null);
        textInputEditTextHip.setText(null);
    }

}
