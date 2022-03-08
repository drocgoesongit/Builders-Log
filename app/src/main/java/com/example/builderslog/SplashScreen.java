package com.example.builderslog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.StaticLayout;

import com.example.builderslog.databinding.ActivitySplashScreenBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        checkForAlreadySignedIn();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, SIgnup.class);
                startActivity(intent);
            }
        },1500);
    }

    // Checking if the user is already authenticated.
    private void checkForAlreadySignedIn(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }
    }
}