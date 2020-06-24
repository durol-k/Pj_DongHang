package com.capstone.donghang.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;

public class FragmentSelectDay extends Fragment {
    public static FragmentSelectDay newInstance() {
        return new FragmentSelectDay();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan_add_date, container, false);

        Button btnSelectOk = root.findViewById(R.id.btnSelectOk);

        btnSelectOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(FragmentMakePlan.newInstance(), MainActivity.PLAN);
            }
        });

        return root;
    }
}
