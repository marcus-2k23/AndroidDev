package com.example.task6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.task6.databinding.FragmentDeleteBinding;


public class DeleteFragment extends Fragment implements View.OnClickListener {

    FragmentDeleteBinding fragmentDeleteBinding;
    DBHelper dbHelper;

    public DeleteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDeleteBinding = FragmentDeleteBinding.inflate(inflater, container, false);
        init();
        return fragmentDeleteBinding.getRoot();
    }

    private void init() {
        dbHelper = new DBHelper(getContext());
        fragmentDeleteBinding.btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == fragmentDeleteBinding.btnDelete.getId()) {
            try {
                int deletedRow = dbHelper.DeleteEmployee(Integer.parseInt(fragmentDeleteBinding.edtEmpId.getText().toString().trim()));
                if (deletedRow > 0) {
                    Toast.makeText(getContext(), "Employee record deleted successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Employee record does not exist", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Employee id should be number", Toast.LENGTH_LONG).show();
            }
        }
    }
}