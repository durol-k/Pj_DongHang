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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentPlan extends Fragment {
    Context context;
    ArrayList<String> currentPlanDataSet;
    ArrayList<ArrayList<String>> planListDataSet;
    RecyclerView currentPlan, planList;
    RecyclerView.Adapter currentPlanAdapter, planListAdapter;
    RecyclerView.LayoutManager currentPlanLayoutManager, planListLayoutManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan, container, false);
        currentPlan = root.findViewById(R.id.currentPlanRecycler);
        planList = root.findViewById(R.id.planListRecycler);
        currentPlan.setHasFixedSize(true);
        planList.setHasFixedSize(true);

        currentPlanLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        planListLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        currentPlan.setLayoutManager(currentPlanLayoutManager);
        planList.setLayoutManager(planListLayoutManager);

        currentPlanDataSet = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            currentPlanDataSet.add("여행지" + i);
        }
        Log.d("test", currentPlanDataSet.get(1));
        currentPlanAdapter = new CurrentPlanRecyclerAdapter(currentPlanDataSet);
        currentPlan.setAdapter(currentPlanAdapter);

        planListDataSet = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 8; i++) {
            ArrayList<String> dataSet = new ArrayList<String>();
            dataSet.add("일정 제목 " + (i + 1));
            String start = String.format("%04d.%02d.%02d", 2020, 6 + i, 11);
            String end = String.format("%04d.%02d.%02d", 2020, 6 + i, 20);
            String date = start + " ~ " + end;
            dataSet.add(date);
            for (int j = 0; j < i % 4 + 1; j++) {
                dataSet.add("# tag" + (j + 1));
            }
            planListDataSet.add(dataSet);
        }
        planListAdapter = new PlanListRecyclerAdapter(planListDataSet);
        planList.setAdapter(planListAdapter);

        return root;
    }
}
