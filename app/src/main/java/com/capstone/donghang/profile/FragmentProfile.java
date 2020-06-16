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


        return inflater.inflate(R.layout.fragment_profile, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        userImg = getView().findViewById(R.id.profile_img);
        id_manage = getView().findViewById(R.id.profile_manage);

        profile_recycleView = getView().findViewById(R.id.profile_recycleView);
        profile_recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profileAdapter = new ProfileAdapter(profile_title_list, getActivity());
        profile_recycleView.setAdapter(profileAdapter);


        profileAdapter.addItem(getString(R.string.recommend_people));
        profileAdapter.addItem(getString(R.string.myPost));
        profileAdapter.addItem(getString(R.string.myComment));
        profileAdapter.addItem(getString(R.string.profile_setting));

        id_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentProfile_idManage fragmentProfileIdManage = new FragmentProfile_idManage();

                currentFragment = fragmentProfileIdManage;

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frame_main, currentFragment).commit();


            }
        });


        //맨 위 메뉴(사진있는 것) 뺀 나머지 메뉴 이벤트 처리
        profileAdapter.setOnItemClickListener(new ProfileAdapter.OnItemClcikListener() {
            @Override
            public void onItemClick(View view, int pos) {
                TextView textView = view.findViewById(R.id.profile_item_title);
                String title = textView.getText().toString();
                Toast.makeText(getContext(), title + "눌림", Toast.LENGTH_SHORT).show();

                FragmentProfile_recommend fragmentProfileRecommend = new FragmentProfile_recommend();
                FragmentProfile_post fragmentProfilePost = new FragmentProfile_post();
                FragmentProfile_comment fragmentProfileComment = new FragmentProfile_comment();
                FragmentProfile_setting fragmentProfileSetting = new FragmentProfile_setting();

                if (title.equals(getString(R.string.recommend_people))) {
                    currentFragment = fragmentProfileRecommend;
                } else if (title.equals(getString(R.string.myPost))) {
                    currentFragment = fragmentProfilePost;
                } else if (title.equals(getString(R.string.myComment))) {
                    currentFragment = fragmentProfileComment;
                } else if (title.equals(getString(R.string.profile_setting))) {
                    currentFragment = fragmentProfileSetting;
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frame_main, currentFragment).commit();

            }
        });


    }


}


