package com.example.suas_ps_mannager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    LinearLayout pendingLL, workingLL, completedLL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pendingLL = findViewById(R.id.pendingLL);
        workingLL = findViewById(R.id.workingLL);
        completedLL = findViewById(R.id.completedLL);

        setClickAction(pendingLL, PendingActivity.class);
        setClickAction(workingLL, WorkingActivity.class);
        setClickAction(completedLL, CompletedActivity.class);
    }
    private void setClickAction(View view, Class<?> destination) {

        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, destination);
            startActivity(intent);
        });

    }

}