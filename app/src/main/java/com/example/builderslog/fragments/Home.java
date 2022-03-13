package com.example.builderslog.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.builderslog.AddLog;
import com.example.builderslog.R;
import com.example.builderslog.WeeklyReport;
import com.example.builderslog.databinding.FragmentHomeBinding;

public class Home extends Fragment {
private FragmentHomeBinding binding;


    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        binding.addLogFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddLog.class);
                startActivity(intent);
            }
        });

        binding.weeklyReportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), WeeklyReport.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}