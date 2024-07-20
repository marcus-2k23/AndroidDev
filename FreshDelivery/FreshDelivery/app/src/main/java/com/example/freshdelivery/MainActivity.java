package com.example.mdgfreshdelivery;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mdgfreshdelivery.databinding.ActivityMainBinding;
import com.example.mdgfreshdelivery.models.HostingCost;
import com.example.mdgfreshdelivery.models.SubscriptionCreator;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    ActivityMainBinding binding;
    HostingCost hCost;
    DatePickerDialog datePicker;
    String plan = "";
    String planType = "";
    String[] monthlyOptions = {"Cooked Meal", "Uncooked Meal"};
    String[] yearlyPlanArray = {"Veggies with Recipe", "Veggies Only"};

    String[] deliveryOptions = {"Store pick-up", "Community Box", "Door Steps"};
    ArrayAdapter monthlyOptionTypes;
    ArrayAdapter yearlyOptions;
    String deliveryTypes = deliveryOptions[0];
    ArrayAdapter deliveryTypesAdapter;
    boolean lemonadeChk;
    boolean milkChk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        hCost = new HostingCost(this);

        SetListeners();
        SetAdaptors();
    }


    private void SetAdaptors() {
        monthlyOptionTypes = hCost.GenerateAdapter(monthlyOptions);
        yearlyOptions = hCost.GenerateAdapter(yearlyPlanArray);
        deliveryTypesAdapter = hCost.GenerateAdapter(deliveryOptions);
        binding.spnDesignTypes.setAdapter(deliveryTypesAdapter);
    }

    public void testToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    // listener for all the fields in application
    private void SetListeners() {
        binding.edtDate.setFocusable(false);
        binding.edtDate.setInputType(InputType.TYPE_NULL);
        binding.edtDate.setOnClickListener(this);
        binding.chkDesigns.setOnClickListener(this);
        binding.chkUpdates.setOnClickListener(this);

        binding.spnDesignTypes.setOnItemSelectedListener(this);
        binding.rgSubsPlan.setOnCheckedChangeListener(this);
        binding.spnPlanType.setOnItemSelectedListener(this);


        binding.btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // date selection
        if (v.getId() == binding.edtDate.getId()) {
            Calendar cal = Calendar.getInstance();


            int saleDay = cal.get(Calendar.DAY_OF_MONTH);
            int saleMonth = cal.get(Calendar.MONTH);
            int saleYear = cal.get(Calendar.YEAR);

            datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> binding.edtDate.setText(String.format("%d/%d/%d", year, month + 1, dayOfMonth)), saleYear, saleMonth, saleDay);
            datePicker.show();
        }

        // checking if milk or lemonade is selected or not
        else if (v.getId() == binding.chkDesigns.getId()) {
            lemonadeChk = binding.chkDesigns.isChecked();
        }
        else if (v.getId() == binding.chkUpdates.getId()) {
            milkChk = binding.chkUpdates.isChecked();
        }
        else if (v.getId() == binding.btnSubmit.getId()) {
            fieldsCheck(binding, plan, planType, deliveryTypes, lemonadeChk, milkChk);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == binding.spnPlanType.getId()) {
            if (Objects.equals(plan, "Monthly")) {
                planType = monthlyOptions[position];
            } else if (Objects.equals(plan, "Yearly")) {
                planType = yearlyPlanArray[position];
            }
        }
        else if (parent.getId() == binding.spnDesignTypes.getId()) {
            deliveryTypes = deliveryOptions[position];
        }
    }

    // subscription plan selection code
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        if (group.getId() == binding.rgSubsPlan.getId()) {
            if (checkedId == binding.rdMonthly.getId()) {
                plan = "Monthly";
                binding.spnPlanType.setAdapter(monthlyOptionTypes);
            } else if (checkedId == binding.rdYearly.getId()) {
                plan = "Yearly";
                binding.spnPlanType.setAdapter(yearlyOptions);
            }
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean fieldsCheck(ActivityMainBinding binding, String plan, String planType, String deliveryTypes, boolean lemonadeChk, boolean milkChk)
    {
        // Customer Name
        String customer_name = binding.edtName.getText().toString();
        if (customer_name.isEmpty()) {
            MessageCreator(binding, "Customer Name is Empty");
            binding.edtName.setError("Customer Name is Empty");
            return false;
        }



        // Subscrtiption Plan check
        if (!plan.equals("Yearly") && !plan.equals("Monthly")) {
            MessageCreator(binding, "Select a subscription Plan");
            return false;
        }

        // checking Plan Type
        if (planType.isEmpty()) {
            MessageCreator(binding, "Select a Plan Type");
            return false;
        }

        // Checking Additionals
        if (deliveryTypes.isEmpty()) {
            MessageCreator(binding, "Select an Additionals");
            return false;
        }

        // Validating Phone
        String phone = binding.edtPhone.getText().toString();
        Pattern phone_regex = Pattern.compile("\\d{10}");
        if (!phone_regex.matcher(phone).matches()) {
            MessageCreator(binding, "Phone number not valid");
            binding.edtPhone.setError("Phone number is 10 digit numeral");
            return false;
        }

        // Subscription Date Error
        String subscription_Date = binding.edtDate.getText().toString();
        if (subscription_Date.isEmpty()) {
            MessageCreator(binding, "Subscription Date Should not be Empty");
            binding.edtDate.setError("Empty Subscription Date not allowed");
            return false;
        } else {
            String[] date_array = subscription_Date.split("/");
            int year = Integer.parseInt(date_array[0]);
            int month = Integer.parseInt(date_array[1]);
            int day = Integer.parseInt(date_array[2]);
            LocalDate current_date = LocalDate.now();
            LocalDate picked_date = LocalDate.of(year, month, day);
            if (current_date.isBefore(picked_date)) {
                binding.edtDate.setError("Subscription Date Should Before the Current Date");
                MessageCreator(binding, "Subscription Date Should Before the Current Date");
                return false;
            }
            binding.edtDate.setError(null);
        }
        SubscriptionCreator subscriptionCreator = new SubscriptionCreator(customer_name,phone, plan, planType, lemonadeChk, milkChk, deliveryTypes, subscription_Date);
        MessageCreator(binding, subscriptionCreator.Creator());
        return true;
    }

    public static void MessageCreator(ActivityMainBinding binding, String message)
    {
        Snackbar snackbar = Snackbar.make(binding.layoutMain, message, BaseTransientBottomBar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        TextView tv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setMaxLines(99);
        snackbar.setAction("OK", v -> {
        }).show();
    }
}


