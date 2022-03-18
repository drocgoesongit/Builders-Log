package com.example.builderslog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.builderslog.databinding.ActivityAddLogBinding;

public class AddLog extends AppCompatActivity {
    private ActivityAddLogBinding binding;
    private MuscleGroupsAdapter adapterForMuscleGroup;
    private LinearLayoutManager llmForMuscleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Add today's log");

        setMuscleGroupRecyclerView();

    }

    private void setMuscleGroupRecyclerView() {
        adapterForMuscleGroup = new MuscleGroupsAdapter(AddLog.this);
        llmForMuscleGroup = new LinearLayoutManager(AddLog.this, RecyclerView.HORIZONTAL, false);
        binding.muscleGroupRecyclerView.setAdapter(adapterForMuscleGroup);
        binding.muscleGroupRecyclerView.setLayoutManager(llmForMuscleGroup);
    }
}