package com.example.suas_ps_mannager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class CompletedActivity extends AppCompatActivity {
    private List<ServiceRequestModel> list = new ArrayList<>();
    private ServiceRequestAdapter adapter;
    private TextView noData;
    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        noData = findViewById(R.id.noData);
        avi = findViewById(R.id.aviView);
        adapter = new ServiceRequestAdapter(this, list, Constants.COMPLETED_STATUS);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        getData();
    }

    private void getData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("service_request");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String room_no = d.child("room_no").getValue().toString();
                    String contact_no = d.child("contact_no").getValue().toString();
                    String service_image = d.child("service_image").getValue().toString();
                    String description = d.child("description").getValue().toString();
                    String status = d.child("status").getValue().toString();
                    String current_time = d.child("current_time").getValue().toString();

                    Log.d("TAG", "onDataChange: "+room_no);
                    if (status.equals(Constants.COMPLETED_STATUS)) {
                        list.add(new ServiceRequestModel(room_no, contact_no, description, service_image, status, current_time));
                    }
                }
                if (list.size()==0){
                    noData.setVisibility(View.VISIBLE);
                } else {
                    noData.setVisibility(View.GONE);
                }
                avi.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}