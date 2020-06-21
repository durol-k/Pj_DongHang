package com.capstone.donghang.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentProfile_setting extends Fragment {

    ArrayList<String> dataLists;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    AppCompatActivity activity;
    ImageView backBtn;
    ActionBar actionBar;


    public FragmentProfile_setting(ActionBar actionBar){
        this.actionBar = actionBar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataLists = new ArrayList<>();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle("설 정");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_profile_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();

        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id){

            case android.R.id.home:
                Toast.makeText(getContext(), "백 눌림", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
