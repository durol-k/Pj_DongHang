package com.capstone.donghang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.capstone.donghang.Search.FragmentSearch;
import com.capstone.donghang.community.Fragment_Community_MainList;
import com.capstone.donghang.plan.FragmentPlan;
import com.capstone.donghang.profile.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        FragmentPlan fragmentPlan = new FragmentPlan();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_main, fragmentPlan).commit();

        //BottomNavigationView item select event
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //selected item disable, set fragment

                fragmentManager = null; // 겹치는거 방지?
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


                switch (item.getItemId()) {
                    case R.id.bot_plan:
                        FragmentPlan fragmentPlan = new FragmentPlan();
                        fragmentTransaction.replace(R.id.frame_main, fragmentPlan).commit();
                        break;
                    case R.id.bot_search:
                        FragmentSearch fragmentSearch = new FragmentSearch();
                        fragmentTransaction.replace(R.id.frame_main, fragmentSearch).commit();

                        break;
                    case R.id.bot_community:

                        break;
                    case R.id.bot_profile:

                        FragmentProfile fragmentProfile = new FragmentProfile(actionBar);
                        fragmentTransaction.replace(R.id.frame_main, fragmentProfile).commit();

                        break;
                    default:
                        return false;
                }

                //replace fragment





                return true;
            }
        });
    }


}