package com.capstone.donghang.plan;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentPlan extends Fragment {
    Context context;
    ArrayList<ArrayList<String>> dataSet;
    RecyclerView recyclerview;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan, container, false);
        recyclerview = root.findViewById(R.id.planListRecycler);
        recyclerview.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(layoutManager);

        dataSet = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 8; i++) {
            ArrayList<String> dataSet2 = new ArrayList<String>();
            dataSet2.add("일정 제목 " + (i + 1));
            String start = String.format("%04d.%02d.%02d", 2020, 6 + i, 11);
            String end = String.format("%04d.%02d.%02d", 2020, 6 + i, 20);
            String date = start + " ~ " + end;
            dataSet2.add(date);
            for (int j = 0; j < i % 4 + 1; j++) {
                dataSet2.add("# tag" + (j + 1));
            }
            dataSet.add(dataSet2);
        }
        adapter = new PlanListRecyclerAdapter(dataSet);
        recyclerview.setAdapter(adapter);

        return root;
    }
}
