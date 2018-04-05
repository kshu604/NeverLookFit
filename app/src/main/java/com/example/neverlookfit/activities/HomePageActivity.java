package com.example.neverlookfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;

import com.example.neverlookfit.R;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = HomePageActivity.this;
    private AppCompatTextView textViewName;
    private Button ProgressPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
        initListeners();
    }

    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        ProgressPhotos = (Button) findViewById(R.id.buttonProgressPhotos);
    }

    private void initObjects() {
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
    }

    private void initListeners() {
        ProgressPhotos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonProgressPhotos:
                Intent intent = new Intent(this, ProgressPhotoActivity.class);
                startActivity(intent);
                break;
        }
    }

}
