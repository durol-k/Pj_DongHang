package com.capstone.donghang.plan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentPlan extends Fragment { // 1-1. 일정 리스트 화면
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan, container, false);

        setHasOptionsMenu(true);

        RecyclerView currentPlan, planList;
        RecyclerView.Adapter currentPlanAdapter, planListAdapter;

        // current plan recycler view setting
        currentPlan = root.findViewById(R.id.currentPlanRecycler);
        currentPlan.setHasFixedSize(true);
        currentPlan.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        // current plan's data setting
        ArrayList<ItemCurrentPlan> currentPlanDataSet = new ArrayList<ItemCurrentPlan>();
        currentPlanDataSet.add(new ItemCurrentPlan("명지전문대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명지대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명지대대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명지지대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명명지대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명지지대대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명명지지대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명명지대대"));
        currentPlanDataSet.add(new ItemCurrentPlan("명명지지대대"));

        // attach adapter to current plan recycler view
        currentPlanAdapter = new RecyclerAdapterCurrentPlan(currentPlanDataSet);
        currentPlan.setAdapter(currentPlanAdapter);

        // plan list recycler view setting
        planList = root.findViewById(R.id.planListRecycler);
        planList.setHasFixedSize(true);
        planList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        // plan list's data setting
        ArrayList<ItemPlan> planListDataSet = new ArrayList<ItemPlan>();
        planListDataSet.add(
                new ItemPlan("제목1", "2020.03.20 ~ 2020.04.19",
                        new ArrayList<String>(Arrays.asList("# tag1", "# tag2"))));
        planListDataSet.add(
                new ItemPlan("제목2", "2020.04.21 ~ 2020.05.19",
                        new ArrayList<String>(Arrays.asList("# tag1", "# tag2", "# tag3"))));
        planListDataSet.add(
                new ItemPlan("제목3", "2020.05.20 ~ 2020.06.01",
                        new ArrayList<String>(Arrays.asList("# tag1", "# tag3", "# tag6", "# tag13", "# tag644"))));
        planListDataSet.add(
                new ItemPlan("제목6", "2020.06.10 ~ 2020.06.19",
                        new ArrayList<String>(Arrays.asList("# tag4", "# tag6"))));
        planListDataSet.add(
                new ItemPlan("제목8", "2020.06.22 ~ 2020.06.30",
                        new ArrayList<String>(Arrays.asList("# tag7"))));
        planListDataSet.add(
                new ItemPlan("제목9", "2020.07.03 ~ 2020.07.13",
                        new ArrayList<String>(Arrays.asList("# tag134", "# tag223"))));
        planListDataSet.add(
                new ItemPlan("제목13", "2020.08.15 ~ 2020.08.22",
                        new ArrayList<String>(Arrays.asList("# tag155", "# tag442"))));
        planListDataSet.add(
                new ItemPlan("제목14", "2020.08.26 ~ 2020.09.13",
                        new ArrayList<String>(Arrays.asList("# tag1123", "# tag3243"))));
        planListDataSet.add(
                new ItemPlan("제목16", "2020.10.11~ 2021.01.08",
                        new ArrayList<String>(Arrays.asList("# tag141", "# tag2124"))));

        // attach adapter to plan list recycler view
        planListAdapter = new RecyclerAdapterPlanList(planListDataSet);

        planList.setAdapter(planListAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.plan_menu_add, menu);
    }
}
