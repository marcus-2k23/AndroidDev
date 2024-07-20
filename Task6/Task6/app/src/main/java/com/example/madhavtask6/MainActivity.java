package com.example.task6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    int[] Layouts;
    SliderAdapter sliderAdapter;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        Layouts = new int[]{
                R.layout.slide_screen_1,
                R.layout.slide_screen_2,
                R.layout.slide_screen_3,
        };

        sliderAdapter = new SliderAdapter(Layouts);
        binding.viewPager.setAdapter(sliderAdapter);

        binding.btnSkip.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // when clicking the next button
        if (v.getId() == binding.btnNext.getId()) {
            int current = getItem(1);
            if (current < Layouts.length) {
                binding.viewPager.setCurrentItem(current);
                if (current == Layouts.length - 1) {
                    binding.btnNext.setText(R.string.cont);
                }
            } else {
                launchLoginScreen();
            }
        } else if (v.getId() == binding.btnSkip.getId()) {
            launchLoginScreen();
        }
    }

    private void launchLoginScreen() {
        intent1 = new Intent(this, LoginActivity.class);
        startActivity(intent1);
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + i;
    }
}