package com.example.builderslog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.builderslog.databinding.ActivityAddLogBinding;
import com.example.builderslog.model.LogElement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;

public class AddLog extends AppCompatActivity {
    private static final String TAG = "AddLog";
    private ActivityAddLogBinding binding;
    private MuscleGroupsAdapter adapterForMuscleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Add today's log");

        setMuscleGroupRecyclerView();

        // Done button onClickListener.
        binding.doneFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checking input.
                if(checkInput()){
                    Toast.makeText(AddLog.this, "Input is proper.", Toast.LENGTH_SHORT).show();
                    uploadLogData();
                }else{
                    Toast.makeText(AddLog.this, "Input is not proper.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Uploading logData to the firebase.
    private void uploadLogData() {
        // getting list from the adapter.
        ArrayList<String> addedMGList = adapterForMuscleGroup.getAddedList();

        //getting today's date.
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM d", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        String presentDate = formatter.format(calendar.getTime());
        Log.i(TAG, "present date: " + presentDate);

        // getting all the inputs.
        String hours = binding.hourText.getText().toString();
        String minutes = binding.minText.getText().toString();
        String weight = binding.weightInput.getText().toString();
        String calories = binding.calorieInput.getText().toString();
        String timeInMilli = String.valueOf(System.currentTimeMillis());

        LogElement logElement = new LogElement(presentDate, hours, minutes, calories,addedMGList, weight, timeInMilli);

        // query to upload the data.
        FirebaseDatabase.getInstance().getReference()
                .child("Logs")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .push()
                .setValue(logElement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // Data uploaded successfully.
                Toast.makeText(AddLog.this, "Log uploaded successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddLog.this, MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Data not uploaded successfully.
                Toast.makeText(AddLog.this, e.getMessage() + "Try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // setting recyclerView of all muscle groups.
    private void setMuscleGroupRecyclerView() {
        adapterForMuscleGroup = new MuscleGroupsAdapter(AddLog.this);
        LinearLayoutManager llmForMuscleGroup = new LinearLayoutManager(AddLog.this, RecyclerView.HORIZONTAL, false);
        binding.muscleGroupRecyclerView.setAdapter(adapterForMuscleGroup);
        binding.muscleGroupRecyclerView.setLayoutManager(llmForMuscleGroup);
    }

    // function to check input accuracy.
    private boolean checkInput() {
        if(binding.hourText.getText().toString().equals("")){
            Toast.makeText(AddLog.this, "Add hours properly.", Toast.LENGTH_SHORT).show();
            return false;
        } else if(binding.minText.getText().toString().equals("")) {
            Toast.makeText(AddLog.this, "Add minutes properly.", Toast.LENGTH_SHORT).show();
            return false;
        } else if(binding.weightInput.getText().toString().equals("")) {
            Toast.makeText(AddLog.this, "Add weight properly.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}