package com.example.builderslog.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.builderslog.AddLog;
import com.example.builderslog.R;
import com.example.builderslog.WeeklyReport;
import com.example.builderslog.databinding.FragmentHomeBinding;
import com.example.builderslog.model.LogElement;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Home extends Fragment {
    private FragmentHomeBinding binding;
    private String presentDate;
    private static final String TAG = "Home";

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        getLogData();

        //TODO to put some loading animation in place of today's activity.

        //getting today's date.
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM d", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        presentDate = formatter.format(calendar.getTime());
        Log.i(TAG, "present date: " + presentDate);

        binding.addLogFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddLog.class);
                startActivity(intent);
            }
        });

        // going to add log activity if clicked on no data available layout.
        binding.todaysDetailNotAvailableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddLog.class);
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

    private void getLogData() {
        // getting only today's log.
        // checking if there is today's log.
        // based on that showing today's log layout.

        FirebaseDatabase.getInstance().getReference().child("Logs")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .orderByChild("timeInMilli")
                .limitToLast(1)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            for(DataSnapshot snapshot1: snapshot.getChildren()){
                                LogElement element = snapshot1.getValue(LogElement.class);
                                Log.i(TAG, element.getDate());
                                if(Objects.equals(element.getDate(), presentDate)) {
                                    binding.dataAvailableGroup.setVisibility(View.VISIBLE);
                                    double minutesInDouble = Integer.parseInt(element.getMinutes()) / 60;
                                    double actualHour = (double) (Integer.parseInt(element.getHours())) + minutesInDouble;
                                    String hours = actualHour + " hrs";
                                    String calories = element.getCalories() + " kcal";
                                    String weight = element.getWeight() + " kg";
                                    binding.weightCountText.setText(weight);
                                    binding.durationCountText.setText(hours);
                                    binding.calorieCountText.setText(calories);
                                }
                                // data is available but not from the same day.
                                else {
                                    Toast.makeText(getContext(), "Today's data is not available.", Toast.LENGTH_SHORT).show();
                                    binding.dataNotAvailableGroup.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "Today's data is not available.", Toast.LENGTH_SHORT).show();
                            binding.dataNotAvailableGroup.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

}