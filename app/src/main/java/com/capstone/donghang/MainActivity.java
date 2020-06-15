package com.capstone.donghang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.capstone.donghang.community.Fragment_Community_MainList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment fragmentPlan, fragmentSearch, fragmentCommunity, fragmentProfile;
    Fragment currentFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set fragment
        fragmentPlan = new FragmentPlan();
        fragmentSearch = new FragmentSearch();
        fragmentCommunity = new Fragment_Community_MainList();
        fragmentProfile = new FragmentProfile();


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_main, fragmentPlan).commit();

        //BottomNavigationView item select event
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //selected item disable, set fragment
                switch (item.getItemId()) {
                    case R.id.bot_plan:
                        currentFragment = fragmentPlan;
                           break;
                    case R.id.bot_search:
                        currentFragment = fragmentSearch;
                        break;
                    case R.id.bot_community:
                        currentFragment = fragmentCommunity;
                        break;
                    case R.id.bot_profile:
                        currentFragment = fragmentProfile;
                        break;
                    default:
                        return false;
                }

                //replace fragment
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, currentFragment).commit();
                return true;
            }
        });
    }
}