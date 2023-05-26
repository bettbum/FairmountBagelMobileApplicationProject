package com.example.fairmountbagelmobileapplicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }
    private void initialize() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d("firebase", "connected");
                } else {
                    Log.d("firebase", "not connected");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("firebase", "Listener was cancelled");
            }
        });

        edEmail = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        //Dev test
        edEmail.setText("admin");
        edPassword.setText("123");

        btnSignIn = findViewById(R.id.btnLogin);
        btnSignIn.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }
}