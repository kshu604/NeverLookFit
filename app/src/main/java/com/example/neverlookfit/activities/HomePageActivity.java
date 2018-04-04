package com.example.neverlookfit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.example.neverlookfit.R;

public class HomePageActivity extends AppCompatActivity {

    private AppCompatActivity activity = HomePageActivity.this;
    private AppCompatTextView textViewName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
    }

    private void initObjects() {
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);

    }


}
