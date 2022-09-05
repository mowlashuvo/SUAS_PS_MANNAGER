package com.example.suas_ps_mannager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RequestDetailActivity extends AppCompatActivity {
    TextView idRoom;
    ImageView imageView;
    Button button;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    ServiceRequestModel model;
    String status;
    private String updatedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);
        model = getIntent().getParcelableExtra("model");
        status = getIntent().getStringExtra("status");
        idRoom = findViewById(R.id.idRoom);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        if (status.equals(Constants.PENDING_STATUS)) {
            button.setBackgroundColor(getResources().getColor(R.color.cyan_500));
            button.setText("Move to On Progress");
            updatedStatus = Constants.WORKING_STATUS;
        } else if (status.equals(Constants.COMPLETED_STATUS)) {
            button.setVisibility(View.GONE);
        } else if (status.equals(Constants.WORKING_STATUS)) {
            updatedStatus = Constants.COMPLETED_STATUS;
            button.setBackgroundColor(getResources().getColor(R.color.green_500));
            button.setText("Move to Completed");
        }
        button.setOnClickListener(view -> {
            button.setVisibility(View.GONE);
            //change the status
            updateStatus();
        });
        idRoom.setText("Room No. "+model.getRoom_no());
        Picasso.get().load(model.getService_image()).placeholder(R.drawable.image_placeholder).into(imageView);
    }

    private void updateStatus() {

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Updating Status");
            progressDialog.setMessage("Please Wait while updating Status");
            progressDialog.show();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("service_request").child(model.getRoom_no());
            databaseReference.child("status").setValue(updatedStatus);
            progressDialog.dismiss();
    }
}