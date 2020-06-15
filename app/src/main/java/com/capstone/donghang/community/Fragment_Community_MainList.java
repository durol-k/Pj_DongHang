package com.capstone.donghang.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Community_MainList extends Fragment {
    List<Fragment_Community_MainListItem> itemList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        itemList = new ArrayList<>();
       // AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_community_mainlist, container, false);
       // Toolbar toolbar = view.findViewById(R.id.community_toolbar);
       // appCompatActivity.setSupportActionBar(toolbar);

        initData();

        RecyclerView recyclerView = view.findViewById(R.id.community_recyclerview);
        Fragment_Community_MainList_RecyclerAdapter recyclerAdapter = new Fragment_Community_MainList_RecyclerAdapter(getContext(),itemList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL)); //구분선
        return view;
    }

    void initData() {
        String str = "명지전문대명지전문대명지전문대명지전문대명지전문대명지전문대명지전문대";
        itemList.add(new Fragment_Community_MainListItem("id" , "00:00", "제목",str, R.drawable.ic_calendar_material_design,0,0,0, R.drawable.ic_person_material_design));

        for(int i = 0 ; i < 10 ; i ++) {
            itemList.add(new Fragment_Community_MainListItem("id" + i, "00:00", "제목" + i, "내용" + i,null,i,i,i, R.drawable.ic_person_material_design));
        }
    }
}
