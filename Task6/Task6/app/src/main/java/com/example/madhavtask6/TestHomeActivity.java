package com.example.task6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task6.databinding.ActivityTestHomeBinding;

public class TestHomeActivity extends AppCompatActivity {

    ActivityTestHomeBinding testHomeBinding;
    SharedPreferences sharedPref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testHomeBinding = ActivityTestHomeBinding.inflate(getLayoutInflater());
        setContentView(testHomeBinding.getRoot());
        init();
    }

    private void init() {
        sharedPref1 = getSharedPreferences("login_details", Context.MODE_PRIVATE);
        String USER_ID = sharedPref1.getString("USER_ID", null);
        String EMAIL_ID = sharedPref1.getString("EMAIL_ID", null);
        testHomeBinding.txtWelcome.setText("Welcome " + USER_ID + " !\n" + EMAIL_ID);
    }
}