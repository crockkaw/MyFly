package com.example.kawka.myfly;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuItem;

public class KomunikatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunikator);

        setupActionBar();
        setupWindowAnimations();

    }


    private void setupWindowAnimations() {
        Transition transition;
        transition = buildEnterTransition();
        getWindow().setEnterTransition(transition);


    }

    private Transition buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(500);
        return enterTransition;
    }


    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("KOMUNIKATOR");

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Transition returnTransition = buildEnterTransition();
            getWindow().setReturnTransition(returnTransition);

            finishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }
}
