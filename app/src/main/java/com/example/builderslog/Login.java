package com.example.builderslog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.builderslog.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        checkForAlreadySignedIn();

        binding.loginButton.setOnClickListener(view -> {
            if(checkForProperInput()){
                signUp();
            }
        });
    }

    private void checkForAlreadySignedIn() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(Login.this , MainActivity.class);
            startActivity(intent);
        }
    }

    private Boolean checkForProperInput() {
        if(binding.emailETLogin.getText().toString().equals("") || binding.emailETLogin.getText().toString().equals("null")){
            Toast.makeText(Login.this, "Write name properly.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.passwordETLogin.getText().toString().equals("") || binding.passwordETLogin.getText().toString().equals("null")){
            Toast.makeText(Login.this, "Write Password properly.", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private void signUp() {

    }
}