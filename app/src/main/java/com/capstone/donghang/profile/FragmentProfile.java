package com.capstone.donghang.profile;


import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentProfile extends Fragment {

    RecyclerView profile_recycleView;
    ProfileAdapter profileAdapter;
    ArrayList<String> profile_title_list;
    Fragment currentFragment;
    ImageView userImg; // 프로필 사진
    TextView user_name_view, user_id_view, id_manage; // 계정관리(텍스트)
    ActionBar actionBar;
    String name, id;


    public FragmentProfile(ActionBar actionBar){
        this.actionBar = actionBar;
        name = "YellowAngryBird";
        id = "yellowBird@naver.com";
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        getActivity().setTitle("내 정보");

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_profile, container, false);
        profile_title_list = new ArrayList<>(); // 선언과 동시에 초기화화면 아이템이 계속 추가되는 오류발생

        return rootView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        userImg = getView().findViewById(R.id.profile_img);
        user_name_view = getView().findViewById(R.id.profile_name);
        user_id_view = getView().findViewById(R.id.profile_id);
        id_manage = getView().findViewById(R.id.profile_manage);

        user_name_view.setText(name);
        user_id_view.setText(id);

        //계정관리 밑줄긋기
        String mystring = getString(R.string.id_management);
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        id_manage.setText(content);

        profile_recycleView = getView().findViewById(R.id.profile_recycleView);
        profile_recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profileAdapter = new ProfileAdapter(profile_title_list);
        profile_recycleView.setAdapter(profileAdapter);


        profileAdapter.addItem(getString(R.string.recommend_people));
        profileAdapter.addItem(getString(R.string.myPost));
        profileAdapter.addItem(getString(R.string.myComment));


        userImg.setImageResource(R.drawable.profile_img);



        id_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentProfile_idManage fragmentProfileIdManage = new FragmentProfile_idManage(actionBar);

                currentFragment = fragmentProfileIdManage;

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frame_main, currentFragment).commit();


            }
        });

        profileAdapter.setOnItemClickListener(new ProfileAdapter.OnItemClcikListener() {
            @Override
            public void onItemClick(View view, int pos) {
                TextView textView = view.findViewById(R.id.profile_item_title);
                String title = textView.getText().toString();
                Toast.makeText(getContext(), title + "눌림", Toast.LENGTH_SHORT).show();

                FragmentProfile_recommend fragmentProfileRecommend = new FragmentProfile_recommend(actionBar);
                FragmentProfile_post fragmentProfilePost = new FragmentProfile_post(actionBar);
                FragmentProfile_comment fragmentProfileComment = new FragmentProfile_comment(actionBar);
                FragmentProfile_setting fragmentProfileSetting = new FragmentProfile_setting(actionBar);

                if(title.equals(getString(R.string.recommend_people))){
                    currentFragment = fragmentProfileRecommend;
                }
                else if(title.equals(getString(R.string.myPost))){
                    currentFragment = fragmentProfilePost;
                }
                else if(title.equals(getString(R.string.myComment))){
                    currentFragment = fragmentProfileComment;
                }
                else if(title.equals(getString(R.string.profile_setting))){
                    currentFragment = fragmentProfileSetting;
                }

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frame_main, currentFragment).commit();

            }
        });


        super.onActivityCreated(savedInstanceState);


    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }


}


