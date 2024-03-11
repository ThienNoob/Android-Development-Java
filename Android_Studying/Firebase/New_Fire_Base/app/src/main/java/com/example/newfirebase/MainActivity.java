package com.example.newfirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get reference to the "message" node in your Firebase database
        DatabaseReference myRef = database.getReference("message");

        // Set a value to the "message" node
        myRef.setValue("xin chao toi là !");
    }
}
