package com.example.neverlookfit.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.neverlookfit.R;
import com.example.neverlookfit.adapters.GoalsRecyclerAdapter;
import com.example.neverlookfit.model.Goal;
import com.example.neverlookfit.sql.DatabaseHelper;


import java.util.ArrayList;
import java.util.List;

public class ViewGoalsActivity extends AppCompatActivity {
    private AppCompatActivity activity = ViewGoalsActivity.this;
    private RecyclerView recyclerViewGoals;
    private List<Goal> listGoals;
    private GoalsRecyclerAdapter goalsRecyclerAdapter;
    private DatabaseHelper databaseHelperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goals);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    private void initViews() {
        recyclerViewGoals = (RecyclerView) findViewById(R.id.recyclerViewGoals);
    }

    private void initObjects() {
        listGoals = new ArrayList<>();
        goalsRecyclerAdapter = new GoalsRecyclerAdapter(listGoals);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewGoals.setLayoutManager(mLayoutManager);
        recyclerViewGoals.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGoals.setHasFixedSize(true);
        recyclerViewGoals.setAdapter(goalsRecyclerAdapter);
        databaseHelperLogin = new DatabaseHelper(activity);

        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listGoals.clear();
                listGoals.addAll(databaseHelperLogin.getAllGoal());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                goalsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
