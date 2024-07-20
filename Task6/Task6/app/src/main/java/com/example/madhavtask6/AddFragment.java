package com.example.task6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.task6.databinding.FragmentAddBinding;


public class AddFragment extends Fragment implements View.OnClickListener {

    FragmentAddBinding fragmentAddBinding;
    DBHelper dbHelper;
    Boolean insertStatus;

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false);
        init();
        return fragmentAddBinding.getRoot();
    }

    private void init() {
        fragmentAddBinding.btnSubmit.setOnClickListener(this);
        dbHelper = new DBHelper(getContext());
    }

    private Employee createEmploy() {
        Employee empObj = new Employee();
        empObj.setName(fragmentAddBinding.edtName.getText().toString().trim());
        empObj.setDesig(fragmentAddBinding.edtDesig.getText().toString().trim());
        empObj.setDept(fragmentAddBinding.edtDept.getText().toString().trim());
        empObj.setEmailid(fragmentAddBinding.edtEmail.getText().toString().trim());
        empObj.setSalary(Integer.parseInt(fragmentAddBinding.edtSalary.getText().toString().trim()));
        return empObj;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == fragmentAddBinding.btnSubmit.getId()) {
            Employee empObj = createEmploy();
            insertStatus = dbHelper.insertEmployee(empObj);
            if (insertStatus) {
                Toast.makeText(getContext(), "Employee record is added successfully.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Record inserted failed.", Toast.LENGTH_LONG).show();
            }
        }
    }
}