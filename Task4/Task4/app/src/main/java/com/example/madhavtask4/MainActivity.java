package com.example.task4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.task4.databinding.ActivityMainBinding;
import com.example.task4.databinding.Restaurant1Binding;
import com.example.task4.databinding.Restaurant2Binding;
import com.example.task4.databinding.Restaurant3Binding;
import com.example.task4.databinding.Restaurant4Binding;
import com.example.task4.databinding.Restaurant5Binding;

public class MainActivity extends AppCompatActivity {

    // Binding object for the main activity layout.
    private ActivityMainBinding binding;

    // The entry point of our app, this method is called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the main activity layout using data binding.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inflate individual restaurant layouts using data binding.
        Restaurant1Binding restaurant1Binding = Restaurant1Binding.inflate(LayoutInflater.from(this));
        Restaurant2Binding restaurant2Binding = Restaurant2Binding.inflate(LayoutInflater.from(this));
        Restaurant3Binding restaurant3Binding = Restaurant3Binding.inflate(LayoutInflater.from(this));
        Restaurant4Binding restaurant4Binding = Restaurant4Binding.inflate(LayoutInflater.from(this));
        Restaurant5Binding restaurant5Binding = Restaurant5Binding.inflate(LayoutInflater.from(this));

        // Get the linear layout container from the main activity layout.
        LinearLayout linearLayout = findViewById(R.id.restaurantBox);
        // Add individual restaurant layouts to the linear layout container.
        linearLayout.addView(restaurant1Binding.getRoot());
        linearLayout.addView(restaurant2Binding.getRoot());
        linearLayout.addView(restaurant3Binding.getRoot());
        linearLayout.addView(restaurant4Binding.getRoot());
        linearLayout.addView(restaurant5Binding.getRoot());

        // Set up rating bar change listeners for each restaurant.
        setupRatingBarChangeListener(restaurant1Binding.ratingBar, restaurant1Binding.textViewRatingValue);
        setupRatingBarChangeListener(restaurant2Binding.ratingBar, restaurant2Binding.textViewRatingValue);
        setupRatingBarChangeListener(restaurant3Binding.ratingBar, restaurant3Binding.textViewRatingValue);
        setupRatingBarChangeListener(restaurant4Binding.ratingBar, restaurant4Binding.textViewRatingValue);
        setupRatingBarChangeListener(restaurant5Binding.ratingBar, restaurant5Binding.textViewRatingValue);
    }

    // A helper method to set up rating bar change listeners for each restaurant.
    private void setupRatingBarChangeListener(android.widget.RatingBar ratingBar, android.widget.TextView textView) {
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            // When the rating bar is changed, update the corresponding text view with the new rating.
            textView.setText(String.valueOf(rating));
        });
    }
}
