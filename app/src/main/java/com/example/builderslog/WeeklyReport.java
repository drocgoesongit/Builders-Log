package com.example.builderslog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.builderslog.databinding.ActivityWeeklyReportBinding;

public class WeeklyReport extends AppCompatActivity {
private ActivityWeeklyReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeeklyReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Weekly report");

    }
}