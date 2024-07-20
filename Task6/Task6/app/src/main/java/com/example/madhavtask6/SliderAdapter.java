package com.example.task6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int[] LayoutScreens;

    public SliderAdapter(int[] layoutScreens) {
        LayoutScreens = layoutScreens;
    }

    @Override
    public int getItemViewType(int position) {
        return LayoutScreens[position];
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return LayoutScreens.length;
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
