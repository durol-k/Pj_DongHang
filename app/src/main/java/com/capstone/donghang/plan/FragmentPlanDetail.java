package com.capstone.donghang.plan;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentPlanDetail extends Fragment {
    private Context context;

    public static FragmentPlanDetail newInstance() {
        return new FragmentPlanDetail();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan_detail, container, false);

        setHasOptionsMenu(true);

        RecyclerView recyclerView = root.findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        ArrayList<ItemPlanDetail> dataSet = new ArrayList<ItemPlanDetail>();
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.10"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "숭례문10", "대한민국 서울0"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "경복궁10", "대한민국 서울0"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산타워10", "대한민국 서울0"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.11"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "숭례문11", "대한민국 서울1"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "경복궁11", "대한민국 서울1"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산타워11", "대한민국 서울1"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.12"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "숭례문12", "대한민국 서울2"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "경복궁12", "대한민국 서울2"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산타워12", "대한민국 서울2"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.13"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "숭례문13", "대한민국 서울3"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "경복궁13", "대한민국 서울3"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산타워13", "대한민국 서울3"));

        RecyclerView.Adapter adapter = new RecyclerAdapterPlanDetail(dataSet);
        recyclerView.setAdapter(adapter);

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
        inflater.inflate(R.menu.plan_menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        ((MainActivity) getActivity()).replaceFragment(FragmentMakePlan.newInstance(), MainActivity.PLAN);

        return true;
    }
}
