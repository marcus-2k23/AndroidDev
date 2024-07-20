package com.example.task5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.task5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    // Initializing our UI components and setting up the click listener for the submit button
    private void initialize() {
        binding.submitButton.setOnClickListener(view -> {
            // Data storage without validation
            preferences = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            // Getting user inputs and saving them for later
            editor.putString("code", binding.productId.getText().toString());
            editor.putFloat("cost", Float.parseFloat(binding.productCost.getText().toString()));

            editor.putString("name", binding.productDesc.getText().toString());

            editor.apply();  // Applying changes asynchronously, no need to wait
            Intent detailsIntent = new Intent(this, DetailsActivity.class);
            startActivity(detailsIntent);
        });
    }
}