package com.example.task6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LoginFailDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setTitle("Login Failed");
        builder1.setMessage("Invalid User ID and/or Password");
        builder1.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(getContext(), "Negative Button Clicked", Toast.LENGTH_LONG).show();
        });
        return builder1.create();
    }
}
