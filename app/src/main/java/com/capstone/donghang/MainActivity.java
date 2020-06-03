package com.capstone.donghang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.capstone.donghang.Fragments.FragmentCommunity;
import com.capstone.donghang.PlanFragments.FragmentPlanList;
import com.capstone.donghang.Fragments.FragmentProfile;
import com.capstone.donghang.Fragments.FragmentSearch;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set main fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_main, new FragmentPlanList()).commit();

        //BottomNavigationView item select event
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                //other items able
                Menu menu = bottomNavigationView.getMenu();
                for (int i = 0; i < menu.size(); i++)
                    bottomNavigationView.getMenu().getItem(i).setEnabled(true);

                //selected item disable, set fragment
                switch (item.getItemId()) {
                    case R.id.bot_plan:
                        item.setEnabled(false);
                        fragment = new FragmentPlanList();
                        break;
                    case R.id.bot_search:
                        item.setEnabled(false);
                        fragment = new FragmentSearch();
                        break;
                    case R.id.bot_community:
                        item.setEnabled(false);
                        fragment = new FragmentCommunity();
                        break;
                    case R.id.bot_profile:
                        item.setEnabled(false);
                        fragment = new FragmentProfile();
                        break;
                    default:
                        return false;
                }

                //replace fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, fragment).commit();
                return true;
            }
        });
    }
}
