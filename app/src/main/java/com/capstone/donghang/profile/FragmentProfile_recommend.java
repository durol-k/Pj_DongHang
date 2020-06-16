package com.capstone.donghang.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.capstone.donghang.profile.FragmentProfile;
import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentProfile_recommend extends Fragment {

    ArrayList<RecommandData> dataLists = new ArrayList<>();
    androidx.recyclerview.widget.RecyclerView recyclerView;
    androidx.appcompat.widget.Toolbar toolbar;
    ProfileRecommandAdapter recommandAdapter;
    AppCompatActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_recommend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();

        recyclerView = getView().findViewById(R.id.post_recycleView);


        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

       recommandAdapter = new ProfileRecommandAdapter(dataLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recommandAdapter);

        recommandAdapter.addItem("동행자1", "서울");
        recommandAdapter.addItem("동행자2", "대전");
        recommandAdapter.addItem("동행자3", "광주");
        recommandAdapter.addItem("동행자4", "부산");




        super.onActivityCreated(savedInstanceState);
    }
}
