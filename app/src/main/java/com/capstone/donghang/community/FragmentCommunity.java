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

public class FragmentCommunity extends Fragment {
    List<Community_Item> itemList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_community, container, false);

        initData();

        RecyclerView recyclerView = view.findViewById(R.id.community_recyclerview);
        Community_RecyclerAdapter recyclerAdapter = new Community_RecyclerAdapter(getContext(),itemList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL)); //구분선
        return view;
    }

    void initData() {
        String str = "명지전문대명지전문대명지전문대명지전문대명지전문대명지전문대명지전문대";
        itemList.add(new Community_Item("id" , "00:00", "제목",str, R.drawable.cat,0,0,0));
        for(int i = 0 ; i < 10 ; i ++) {
            itemList.add(new Community_Item("id" + i, "00:00", "제목" + i, "내용" + i,null,i,i,i));
        }
    }
}
