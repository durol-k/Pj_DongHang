package com.capstone.donghang.plan;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
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

        planList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        root.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.fragmentPlan = new FragmentAddPlanSetting();
                FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frame_main, mainActivity.fragmentPlan).commit();
            }
        });

        return root;
    }
}
