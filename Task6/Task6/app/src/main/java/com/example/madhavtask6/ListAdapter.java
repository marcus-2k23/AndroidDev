package com.example.task6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task6.databinding.RecordLayoutBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Employee> employeeList;
    RecordLayoutBinding recordBinding;

    public ListAdapter(List<Employee> employeeList, Context context) {
        super();
        this.employeeList = employeeList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        recordBinding = RecordLayoutBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recordBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(employeeList.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecordLayoutBinding recyclerRowBinding;

        public ViewHolder(RecordLayoutBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }

        private void bindView(Employee employee) {
            recyclerRowBinding.txtId.setText(String.valueOf(employee.getId()));
            recyclerRowBinding.txtName.setText(employee.getName());
            recyclerRowBinding.txtDesig.setText(employee.getDesig());
            recyclerRowBinding.txtDept.setText(employee.getDept());
            recyclerRowBinding.txtEmail.setText(employee.getEmailid());
            recyclerRowBinding.txtSalary.setText(String.valueOf(employee.getSalary()));
        }
    }
}
