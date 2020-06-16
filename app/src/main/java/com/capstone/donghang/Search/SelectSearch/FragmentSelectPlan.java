package com.capstone.donghang.Search.SelectSearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragmentSelectPlan extends Fragment {
    ExpandableListView elv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_select_plan, container, false);

        elv = view.findViewById(R.id.elvSSP);

        final ArrayList<Position> position = getData();

        ExpandableAdapter adapter = new ExpandableAdapter(view.getContext(),position);
        elv.setAdapter(adapter);



        return view;
    }

    private ArrayList<Position> getData() {
        Position p1 = new Position("1");
        p1.place.add("제주공항");
        p1.place.add("애월");
        p1.place.add("한림공원");

        Position p2 = new Position("2");
        p2.place.add("중문관광단지");
        p2.place.add("천지연폭포");

        Position p3 = new Position("3");
        p3.place.add("성산일출봉");
        p3.place.add("우도");

        Position p4 = new Position("4");
        p4.place.add("비자림");
        p4.place.add("동문시장");

        ArrayList<Position> allposition = new ArrayList<>();
        allposition.add(p1);
        allposition.add(p2);
        allposition.add(p3);
        allposition.add(p4);


        return allposition;
    }
}
