package com.example.assignment1.models;

import android.view.View;
import android.widget.TextView;


import com.example.assignment1.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationUtils {
    // validating different fields with their respective regex
    public static boolean validateOrderDetails(ActivityMainBinding binding, String beverage_type, Boolean additional_mike, Boolean additional_sugar, String beverage_size, String adding_flavour, String City, String Store) {
        // validation for name
        String customer_name = binding.customerName.getText().toString();
        if (customer_name.isEmpty()) {
            DisplayMessage(binding, "Customer Name is Empty");
            binding.customerName.setError("Customer Name is Empty");
            return false;
        }

        // validation for email
        String email_address = binding.emailAddressInput.getText().toString();
        Pattern email_regex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!email_regex.matcher(email_address).matches()) {
            DisplayMessage(binding, "Email Address Format is Incorrect");
            binding.emailAddressInput.setError("Email Address Format is Incorrect");
            return false;
        }

        String sale_date = binding.orderDate.getText().toString();
        if (sale_date.isEmpty()) {
            DisplayMessage(binding, "Sale Date Should be Selected");
            binding.orderDate.setError("Sale Date Should be Selected");
            return false;
        } else {
            String[] date_array = sale_date.split("/");
            int year = Integer.parseInt(date_array[0]);
            int month = Integer.parseInt(date_array[1]);
            int day = Integer.parseInt(date_array[2]);
            LocalDate current_date = LocalDate.now();
            LocalDate picked_date = LocalDate.of(year, month, day);
            if (current_date.isBefore(picked_date)) {
                binding.orderDate.setError("Sale Date Should Before the Current Date");
                DisplayMessage(binding, "Sale Date Should Before the Current Date");
                return false;
            }
            binding.orderDate.setError(null);
        }

        // validation for phone
        String phone_number = binding.phoneNumber.getText().toString();
        Pattern phone_regex = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
        if (!phone_regex.matcher(phone_number).matches()) {
            DisplayMessage(binding, "Phone Format is Incorrect");
            binding.phoneNumber.setError("Phone Format is Incorrect");
            return false;
        }

        // checking if fields are empty
        if (City.isEmpty()) {
            DisplayMessage(binding, "City is Empty or Incorrect");
            return false;
        }

        if (Store.isEmpty()) {
            DisplayMessage(binding, "Store Should be Selected");
            return false;
        }

        if (beverage_size.isEmpty()) {
            DisplayMessage(binding, "Beverage Size Should be Selected");
            return false;
        }

        Order order = new Order(customer_name, email_address, phone_number, beverage_type, additional_mike, additional_sugar, beverage_size, adding_flavour, City, Store, sale_date);

        // generating receipt
        String receipt = order.createOrderReceipt();
        DisplayMessage(binding, receipt);
        return true;
    }

    // displaying message in snackbar
    public static void DisplayMessage(ActivityMainBinding binding, String message) {
        Snackbar snackbar = Snackbar.make(binding.root, message, BaseTransientBottomBar.LENGTH_INDEFINITE);
        View snackbarView = snackbar.getView();
        TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setMaxLines(99);
        snackbar.setAction("OK", v -> {
        }).show();
    }
}

