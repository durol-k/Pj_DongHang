package com.capstone.donghang.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentProfile extends Fragment {

    RecyclerView profile_recycleView;
    ProfileAdapter profileAdapter;
    ArrayList<String> profile_title_list = new ArrayList<>();
    Fragment currentFragment;
    ImageView userImg; // 프로필 사진
    TextView id_manage; // 계정관리(텍스트)


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_profile, container, false);

        return rootView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        userImg = getView().findViewById(R.id.profile_img);
        id_manage = getView().findViewById(R.id.profile_manage);

        profile_recycleView = getView().findViewById(R.id.profile_recycleView);
        profile_recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profileAdapter = new ProfileAdapter(profile_title_list);
        profile_recycleView.setAdapter(profileAdapter);


        profileAdapter.addItem(getString(R.string.recommend_people));
        profileAdapter.addItem(getString(R.string.myPost));
        profileAdapter.addItem(getString(R.string.myComment));
        profileAdapter.addItem(getString(R.string.profile_setting));

        super.onActivityCreated(savedInstanceState);


    }


}


