package com.example.task5;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task5.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding detailsBinding;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());
        retrievePreferences(); // fetching the saved data
    }

    private void retrievePreferences() {
        preferences = getSharedPreferences("user_data", MODE_PRIVATE);

        // Geting the data
        String productCode = preferences.getString("code", "");
        String productName = preferences.getString("name", "");
        float productCost = preferences.getFloat("cost", 0);

        // Display the data
        detailsBinding.productCodeText.setText("Product ID: " + productCode);
        detailsBinding.productNameText.setText("Product Name: " + productName);

        detailsBinding.productPriceText.setText("Cost: $" + productCost);
    }
}
