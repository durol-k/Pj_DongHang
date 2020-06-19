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
    BottomNavigationView bottomNavigationView;
    Fragment fragmentPlan, fragmentSearch, fragmentCommunity, fragmentProfile;
    Fragment currentFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
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
                replaceFragment(currentFragment);
                return true;
            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.replace(R.id.frame_main, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }
}