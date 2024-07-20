package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.task3.databinding.ActivityMainBinding;
import com.example.task3.models.HostingCost;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    // Declaring variables
    double planCost = 0;
    double additionalCOst = 0;
    double dbCost = 0;
    double stagingCost = 0;
    String webSpace;
    double webSpaceCost = 0;
    String province;
    double totalCost = 0;
    ActivityMainBinding binding;

    DatePickerDialog datePicker;
    HostingCost hCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rdStartUp.setChecked(false);
        binding.rdGrowBig.setChecked(false);
        binding.rdPremium.setChecked(false);

        ArrayAdapter<CharSequence> adapterSpace = ArrayAdapter.createFromResource(this,
                R.array.webspace_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spnSpace.setAdapter(adapterSpace);

        binding.acProvince.setThreshold(2);
        binding.acProvince.setInputType(InputType.TYPE_NULL);

        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,
                R.array.webspace_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spnSpace.setAdapter(adapterProvince);

        binding.edtDate.setInputType(InputType.TYPE_NULL);

        setListeners();
    }

    // Implementing AdapterView.OnItemSelectedListener interface methods
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        webSpace=binding.spnSpace.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),webSpace, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // Set listeners for UI components
    private void setListeners() {
        binding.spnSpace.setOnItemSelectedListener(this);
        binding.acProvince.setOnItemClickListener(this);
        binding.chkDb.setOnClickListener(this);
        binding.chkStaging.setOnClickListener(this);
        binding.rgHostingPlan.setOnCheckedChangeListener(this);

        binding.edtDate.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);
    }

    // Implementing AdapterView.OnItemClickListener interface method
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get selected province from AutoCompleteTextView and display a toast message
        province=parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),province,Toast.LENGTH_LONG).show();
    }

    // Implementing View.OnClickListener interface method
    @Override
    public void onClick(View v) {
        // Handle click events for various UI elements
        if (v.getId()==binding.chkDb.getId())
        {
            // Handle checkbox for database option
            if (binding.chkDb.isChecked()){
                dbCost = 13.20;
            }
            else {
                dbCost = 0;
            }
        }
        else if (v.getId()==R.id.chkStaging) {
            // Handle checkbox for staging option
            if (binding.chkStaging.isChecked()){
                stagingCost = 8.90;
            }
            else {
                stagingCost = 0;
            }
        } else if (v.getId()==R.id.edtDate) {
            // Handle click on date EditText to show date picker dialog
            Calendar cal = Calendar.getInstance();
            int dayOfSales= cal.get(Calendar.DAY_OF_MONTH);
            int monthOfSales= cal.get(Calendar.MONTH);
            int yearOfSales= cal.get(Calendar.YEAR);
            datePicker = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day){
                    binding.edtDate.setText(day+"/"+(month+1)+"/"+year);
                }
            },yearOfSales,monthOfSales,dayOfSales);
            datePicker.show();
        }
        else if (v.getId()==R.id.btnSubmit){
           if(isFormValidate()){
               hCost=new HostingCost(binding.edtName.getText().toString(), province,webSpace,binding.edtDate.getText().toString(),dbCost,stagingCost,planCost);
               String result=hCost.getHostingCost();
               Snackbar.make(binding.ltHost,result,Snackbar.LENGTH_INDEFINITE)
                       .setAction("Okay", new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
                           }
                       }).show();
           }
        }

    }

    // Implementing RadioGroup.OnCheckedChangeListener interface method
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        // Handle radio button selection for hosting plans
        if (binding.rgHostingPlan.getCheckedRadioButtonId()==R.id.rdStartUp){
            planCost = 25.38;
        }
        else if (binding.rgHostingPlan.getCheckedRadioButtonId()==R.id.rdGrowBig){
            planCost = 30.18;
        }
        else {
            planCost = 32.65;
        }

    }


    // Validating the input fields
    private boolean isFormValidate(){
        //validation code for name field
        if(binding.edtName.length()==0){
            binding.edtName.setError("This field is required");
            return false;
        }

        //validation code for radio buttons
        if(!binding.rdGrowBig.isChecked() && !binding.rdPremium.isChecked() && !binding.rdStartUp.isChecked()){
            binding.rdStartUp.setError("This field is required");
            binding.rdPremium.setError("This field is required");
            binding.rdGrowBig.setError("This field is required");
            return false;
        }

        //validation code for check buttons
        if(!binding.chkStaging.isChecked() && !binding.chkDb.isChecked()){
            binding.chkStaging.setError("This field is required");
            binding.chkDb.setError("This field is required");
            return false;
        }

        //validation code for province field
        if(binding.acProvince.length()==0){
            binding.acProvince.setError("This field is required");
            return false;
        }
        //validation code for date field
        if(binding.edtDate.length()==0){
            binding.edtDate.setError("This field is required");
            return false;
        }

        return true;
    }
}