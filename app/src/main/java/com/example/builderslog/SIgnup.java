package com.example.builderslog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.builderslog.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class SIgnup extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        pd = new ProgressDialog(this);

        checkForAlreadySignedIn();

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkForProperInput()){
                    signUp();
                }
            }

        });

        binding.googleIconSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SIgnup.this, "On google clicked.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void signUp() {
        pd.setMessage("Creating your profile.");
        pd.show();
        String username = binding.usernameETSignup.getText().toString();
        String password = binding.passwordETSignup.getText().toString();
        String email = binding.emailETSignup.getText().toString();
        // Creating the user with email and password.
        // After successful creation of user
        // updating the user name and email into realtime database.
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("username", username);
                map.put("email", email);
                FirebaseDatabase.getInstance().getReference().child("Users").
                        child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).
                        setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SIgnup.this, "Profile created successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SIgnup.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private boolean checkForProperInput(){
        if(binding.usernameETSignup.getText().toString().equals("") || binding.usernameETSignup.getText().toString().equals("null")){
            Toast.makeText(SIgnup.this, "Write name properly.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.passwordETSignup.getText().toString().equals("") || binding.passwordETSignup.getText().toString().equals("null")){
            Toast.makeText(SIgnup.this, "Write Password properly.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.emailETSignup.getText().toString().equals("") || binding.emailETSignup.getText().toString().equals("null")){
            Toast.makeText(SIgnup.this, "Write Email properly.", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    // Checking if the user is already authenticated.
    private void checkForAlreadySignedIn(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(SIgnup.this, MainActivity.class);
            startActivity(intent);
        }
    }
}