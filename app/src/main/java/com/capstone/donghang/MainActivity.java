package com.capstone.donghang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.capstone.donghang.Search.FragmentSearch;
import com.capstone.donghang.community.Fragment_Community_MainList;
import com.capstone.donghang.plan.FragmentPlan;
import com.capstone.donghang.profile.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
//    public final static int PLAN = 1;
//    public final static int SEARCH = 2;
//    public final static int COMMUNITY = 3;
//    public final static int PROFILE = 4;

    BottomNavigationView bottomNavigationView;
    Fragment fragmentPlan, fragmentSearch, fragmentCommunity, fragmentProfile;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        //set fragment
        fragmentPlan = new FragmentPlan();
        fragmentSearch = new FragmentSearch();
        fragmentCommunity = new Fragment_Community_MainList();
        fragmentProfile = new FragmentProfile();

        replaceFragment(fragmentPlan);

        //BottomNavigationView item select event
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //selected item disable, set fragment
                switch (item.getItemId()) {
                    case R.id.bot_plan:
                        replaceFragment(fragmentPlan);
                        break;
                    case R.id.bot_search:
                        replaceFragment(fragmentSearch);
                        break;
                    case R.id.bot_community:
                        replaceFragment(fragmentCommunity);
                        break;
                    case R.id.bot_profile:
                        replaceFragment(fragmentProfile);
                        break;
                    default:
                        return false;
                }

                return true;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame_main, fragment).commit();

//        switch (flag) {
//            case PLAN:
//                fragmentPlan = fragment;
//                break;
//            case SEARCH:
//                fragmentSearch = fragment;
//                break;
//            case COMMUNITY:
//                fragmentCommunity = fragment;
//                break;
//            case PROFILE:
//                fragmentProfile = fragment;
//                break;
//        }
    }
}