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
    ImageView backBtn, settingBtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_recommend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();


        toolbar = getView().findViewById(R.id.toolbar);
        backBtn = getView().findViewById(R.id.back_btn);
        settingBtn = getView().findViewById(R.id.setting_btn);

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


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "뒤로가기 눌림", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(FragmentProfile_recommend.this);
                fragmentTransaction.replace(R.id.frame_main, new FragmentProfile()).commit();

            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                dlg.setTitle("동행자 관련 설정 대화상자");
                dlg.setMessage("희망여행지와 날짜 설정할 예정");
                dlg.show();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
