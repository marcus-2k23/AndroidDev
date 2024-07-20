package com.example.mdgfreshdelivery.models;

import android.content.Context;
import android.widget.ArrayAdapter;


// File for drop-down spinner not Cost calculation
public class HostingCost {

    private final Context context;

    public HostingCost(Context context) {
        this.context = context;
    }
    public ArrayAdapter GenerateAdapter(String[] source) {
        return new ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, source);
    }

}
