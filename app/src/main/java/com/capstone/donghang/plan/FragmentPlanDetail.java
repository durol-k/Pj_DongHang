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
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "숭례문", "서울 종로구 사직로 161 경복궁"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "경복궁", "서울 중구 세종대로 40"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산타워", "서울 용산구 남산공원길 103 서울타워우편물취급소"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.11"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "명지전문대", "서울특별시 서대문구 가좌로 134"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "명지대", "서울특별시 서대문구 거북골로 34"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산", "서울 중구 회현동1가"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.12"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "광화문", "서울 종로구 사직로 161"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "서울시청", "서울 중구 세종대로 110 서울특별시청"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "덕수궁", "서울 중구 세종대로 99 덕수궁"));

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
        ((MainActivity) getActivity()).replaceFragment(new FragmentMakePlan());

        return true;
    }
}
