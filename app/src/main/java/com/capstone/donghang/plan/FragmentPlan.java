package com.capstone.donghang.plan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentPlan extends Fragment { // 1-1. 일정 리스트 화면
    private Context context;
    ArrayList<ItemPlan> planListDataSet;

    public static FragmentPlan newInstance(){
        return new FragmentPlan();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan_list, container, false);

        setHasOptionsMenu(true);

        // current plan recycler view setting
//        RecyclerView currentPlan = root.findViewById(R.id.currentPlanRecycler);
//        currentPlan.setHasFixedSize(true);
//        currentPlan.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//
//        // current plan's data setting
//        ArrayList<ItemCurrentPlan> currentPlanDataSet = new ArrayList<ItemCurrentPlan>();
//        currentPlanDataSet.add(new ItemCurrentPlan("명지전문대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명지대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명지대대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명지지대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명명지대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명지지대대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명명지지대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명명지대대"));
//        currentPlanDataSet.add(new ItemCurrentPlan("명명지지대대"));
//
//        // attach adapter to current plan recycler view
//        RecyclerAdapterCurrentPlan currentPlanAdapter = new RecyclerAdapterCurrentPlan(currentPlanDataSet);
//        currentPlan.setAdapter(currentPlanAdapter);

        // plan list recycler view setting
        RecyclerView planList = root.findViewById(R.id.planListRecycler);
        planList.setHasFixedSize(true);
        planList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        planList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        // plan list's data setting
        planListDataSet = new ArrayList<ItemPlan>();
        planListDataSet.add(
                new ItemPlan("현재일정", "2020.06.10 ~ 2020.06.19",
                        new ArrayList<String>(Arrays.asList("# 힐링", "# 서울"))));
        planListDataSet.add(
                new ItemPlan("제목1", "2020.03.20 ~ 2020.04.19",
                        new ArrayList<String>(Arrays.asList("# 힐링", "# 경기"))));
        planListDataSet.add(
                new ItemPlan("제목2", "2020.04.21 ~ 2020.05.19",
                        new ArrayList<String>(Arrays.asList("# 국내", "# 서울", "# 인기"))));
        planListDataSet.add(
                new ItemPlan("제목3", "2020.05.20 ~ 2020.06.01",
                        new ArrayList<String>(Arrays.asList("# 힐링", "# 전통", "# 자연"))));
        planListDataSet.add(
                new ItemPlan("제목6", "2020.06.10 ~ 2020.06.19",
                        new ArrayList<String>(Arrays.asList("# 힐링", "# 서울"))));
        planListDataSet.add(
                new ItemPlan("제목8", "2020.06.22 ~ 2020.06.30",
                        new ArrayList<String>(Arrays.asList("# 국내"))));
        planListDataSet.add(
                new ItemPlan("제목9", "2020.07.03 ~ 2020.07.13",
                        new ArrayList<String>(Arrays.asList("# 맛집", "# 강원"))));
        planListDataSet.add(
                new ItemPlan("제목13", "2020.08.15 ~ 2020.08.22",
                        new ArrayList<String>(Arrays.asList("# 바다", "# 더워"))));

        // attach adapter to plan list recycler view
        RecyclerAdapterPlanList planListAdapter = new RecyclerAdapterPlanList(planListDataSet);
        planList.setAdapter(planListAdapter);
        planListAdapter.setOnItemClickListener(new RecyclerAdapterPlanList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((MainActivity) getActivity()).replaceFragment(new FragmentPlanDetail());
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        ((MainActivity) getActivity()).replaceFragment(new FragmentAddPlanSetting());
        return true;
    }
}
