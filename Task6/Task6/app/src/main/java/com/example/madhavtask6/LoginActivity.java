package com.example.task6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task6.databinding.ActivityLoginBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    Intent intent2;
    SharedPreferences sharedPref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        init();
    }

    private void init() {
        loginBinding.btnLogin.setOnClickListener(this);
        sharedPref1 = getSharedPreferences("login_details", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == loginBinding.btnLogin.getId()) {
            String username = loginBinding.edtUserId.getText().toString().trim();
            String password = loginBinding.edtPasswd.getText().toString().trim();
            String email = loginBinding.edtEmailid.getText().toString().trim();
            if (username.equals("") && password.equals("password")) {

                SharedPreferences.Editor editor = sharedPref1.edit();
                editor.putString("USER_ID", username);
                editor.putString("EMAIL_ID", email);
                editor.commit();

                intent2 = new Intent(this, HomeActivity.class);
                startActivity(intent2);
            } else {
//
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Login Failed")
                        .setMessage("Invalid User ID and/or Password")
                        .setNegativeButton("Cancel", (dialog, which) -> {
                        })
                        .setPositiveButton("Ok", (dialog, which) -> {

                        })
                        .show();
            }
        }
    }
}