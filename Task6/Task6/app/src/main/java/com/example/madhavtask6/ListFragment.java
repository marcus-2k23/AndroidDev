package com.example.task6;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task6.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    FragmentListBinding fragmentListBinding;
    List<Employee> emList = new ArrayList<Employee>();
    DBHelper dbHelper;
    ListAdapter listAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentListBinding = FragmentListBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        init();
        return fragmentListBinding.getRoot();
    }

    private void init() {
        dbHelper = new DBHelper(getContext());
        Cursor cursor1 = dbHelper.readEmployees();
        if (cursor1.getCount() == 0) {

            Toast.makeText(getContext(), "No employee records found", Toast.LENGTH_SHORT).show();
        } else {
            cursor1.moveToFirst(); // get the first item in the list
            do {
                Employee empObj = new Employee();
                empObj.setId(cursor1.getInt(0));
                empObj.setName(cursor1.getString(1));
                empObj.setDesig(cursor1.getString(2));
                empObj.setDept(cursor1.getString(3));
                empObj.setEmailid(cursor1.getString(4));
                empObj.setSalary(cursor1.getInt(5));
                emList.add(empObj);
            } while (cursor1.moveToNext());
            cursor1.close();
            dbHelper.close();
            bindAdapter();
        }
    }

    private void bindAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        fragmentListBinding.rcView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(emList, getContext());
        fragmentListBinding.rcView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}