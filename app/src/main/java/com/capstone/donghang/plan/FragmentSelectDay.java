package com.capstone.donghang.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;
import com.google.android.material.datepicker.MaterialDatePicker;

public class FragmentSelectDay extends Fragment {
    int flag = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan_add_date, container, false);

        Button btnSelectOk = root.findViewById(R.id.btnSelectOk);
        final TextView textStart = root.findViewById(R.id.textStart);
        final TextView textEnd = root.findViewById(R.id.textEnd);
        CalendarView calendarView = root.findViewById(R.id.planDateCalendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (flag == 0){
                    textStart.setText(String.format("%4d.%2d.%2d", year, month+1, dayOfMonth));
                    flag++;
                }
                else {
                    textEnd.setText(String.format("%4d.%2d.%2d", year, month + 1, dayOfMonth));
                    flag--;
                }
            }
        });
        btnSelectOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new FragmentMakePlan());
            }
        });

        return root;
    }
}
