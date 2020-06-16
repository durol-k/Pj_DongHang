package com.capstone.donghang.community;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.capstone.donghang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Community_Write extends Fragment {

    public Fragment_Community_Write() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_write, container, false);

        Spinner category = view.findViewById(R.id.community_write_category);
        Spinner header = view.findViewById(R.id.community_write_head);
        ArrayAdapter catAdapter = ArrayAdapter.createFromResource(getContext(), R.array.community_category, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter headAdapter = ArrayAdapter.createFromResource(getContext(), R.array.community_header_notice, android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(catAdapter);
        header.setAdapter(headAdapter);

        return view;
    }
}
