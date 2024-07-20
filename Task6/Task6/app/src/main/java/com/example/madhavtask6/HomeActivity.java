package com.example.task6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.task6.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding homeBinding;
    ActionBarDrawerToggle mToggle;
    SharedPreferences sharedPref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
        init();
    }

    private void init() {
        sharedPref2 = getSharedPreferences("login_details", MODE_PRIVATE);
        homeBinding.txtWelcome.setText("Welcome " + sharedPref2.getString("USER_ID", null) + " !");

        mToggle = new ActionBarDrawerToggle(this, homeBinding.drawerLayout, homeBinding.materialToolbar, R.string.nav_open, R.string.nav_close);
        homeBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(homeBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationDrawer();
        setBottomNavigation();

    }

    private void setBottomNavigation() {
        homeBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_bottom_search) {

                } else if (item.getItemId() == R.id.nav_bottom_account) {

                } else if (item.getItemId() == R.id.nav_bottom_profile) {

                }
                return false;
            }
        });
    }

    private void setNavigationDrawer() {
        homeBinding.navView.setNavigationItemSelectedListener(item -> {
            Fragment frag = null;
            if (item.getItemId() == R.id.nav_add_emp) {
                frag = new AddFragment();
            } else if (item.getItemId() == R.id.nav_delete_emp) {
                frag = new DeleteFragment();
            } else if (item.getItemId() == R.id.nav_list_emp) {
                frag = new ListFragment();
            }
            if (frag != null) {
                FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
                frgTrans.replace(R.id.frame, frag);
                frgTrans.commit();
                homeBinding.drawerLayout.closeDrawers();
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}