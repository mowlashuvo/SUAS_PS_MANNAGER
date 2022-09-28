package com.example.suas_ps_mannager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class ServiceRequestActivity extends AppCompatActivity {
    LinearLayout pendingLL, workingLL, completedLL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);
        pendingLL = findViewById(R.id.pendingLL);
        workingLL = findViewById(R.id.workingLL);
        completedLL = findViewById(R.id.completedLL);

        setClickAction(pendingLL, PendingActivity.class);
        setClickAction(workingLL, WorkingActivity.class);
        setClickAction(completedLL, CompletedActivity.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setClickAction(View view, Class<?> destination) {

        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, destination);
            startActivity(intent);
        });

    }

}