package com.capstone.donghang.Search;

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
import com.capstone.donghang.plan.FragmentPlan;

import java.util.ArrayList;

public class FragmentSearch extends Fragment {
    RecyclerView rcv1,rcv2;

    public static FragmentSearch newInstance() {
        return new FragmentSearch();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        setHasOptionsMenu(true);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        String[] data1 = {"다대포항","해운대","월미곶","백록담","선유도"};
        String[] data2 = {"강원도","부산","경주","인천","제주"};

        for(int i=0; i<5; i++){
            list1.add(String.format(data1[i]));
            list2.add(String.format(data2[i]));
        }

        rcv1 = view.findViewById(R.id.rcv여행지);
        rcv2 = view.findViewById(R.id.rcv코스);
        rcv1.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcv2.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));

        SearchMainAdapter adt1, adt2;
        adt1 = new SearchMainAdapter(list1,null);
        adt2 = new SearchMainAdapter(list2,null);

        rcv1.setAdapter(adt1);
        rcv2.setAdapter(adt2);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }
}
