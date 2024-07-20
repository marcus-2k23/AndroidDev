package com.example.assignment1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment1.databinding.ActivityMainBinding;
import com.example.assignment1.models.DataProvider;
import com.example.assignment1.models.ValidationUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String selectedBeverage = "coffee";
    private boolean addMilk;
    private boolean addSugar;
    private String selectedSize = "small";
    private String selectedFlavor = "None";
    private String selectedCity = "";
    private String selectedStore = "";
    private DatePickerDialog datePickerDialog;
    private DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Context context = this;

        // Initialize DataProvider
        dataProvider = new DataProvider(this);

        initializeViews();
        setupEventListeners();
    }

    //  Initializing views in the activity.
    private void initializeViews() {
        ArrayAdapter<String> regionAdapter = dataProvider.getCityAdapter();
        binding.regionAutoComplete.setAdapter(regionAdapter);
    }


    // setting up event listeners
    private void setupEventListeners() {
        binding.typeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.coffeeSelect) {
                selectedBeverage = "coffee";
            } else if (checkedId == R.id.teaSelect) {
                selectedBeverage = "tea";
            }
        });

        // Event listener for beverage size selection
        binding.sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.smallSizeOption) {
                selectedSize = "small";
            } else if (checkedId == R.id.mediumSizeOption) {
                selectedSize = "medium";
            } else if (checkedId == R.id.largeSizeOption) {
                selectedSize = "large";
            }
        });

        // Event listeners for milk and sugar checkboxes
        binding.milkCheckbox.setOnClickListener(v -> addMilk = binding.milkCheckbox.isChecked());
        binding.sugarCheckbox.setOnClickListener(v -> addSugar = binding.sugarCheckbox.isChecked());

        binding.orderDate.setFocusable(false);
        binding.orderDate.setOnClickListener(v -> showDatePicker());

        // Event listener for selecting a city from the dropdown
        binding.regionAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = parent.getItemAtPosition(position).toString();
            updateStoreSpinner(dataProvider.getStoresForCity(selectedCity));
            updateFlavorSpinner(dataProvider.getFlavorList(selectedBeverage));
        });

        // Event listener for selecting a store from the spinner
        binding.storeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStore = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedStore = "";
            }
        });

        binding.orderButton.setOnClickListener(v -> {
            if (selectedCity.isEmpty() || selectedStore.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please select city and store", Toast.LENGTH_SHORT).show();
            } else {
                boolean isValid = ValidationUtils.validateOrderDetails(binding, selectedBeverage, addMilk, addSugar, selectedSize, selectedFlavor, selectedCity, selectedStore);
                if (isValid) {
                    Toast.makeText(MainActivity.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // generating the store in the store spinner
    private void updateStoreSpinner(List<String> stores) {
        ArrayAdapter<String> storeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stores);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.storeSpinner.setAdapter(storeAdapter);
    }


    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    binding.orderDate.setText(String.format("%d/%d/%d", year1, monthOfYear + 1, dayOfMonth));
                }, year, month, day);
        datePickerDialog.show();
    }

    private void updateFlavorSpinner(List<String> flavors) {
        ArrayAdapter<String> flavorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, flavors);
        flavorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.flavorSpinner.setAdapter(flavorAdapter);
    }


}
