package com.capstone.donghang.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.donghang.R;

public class FragmentAddPlanSetting extends Fragment {

    EditText editPlanName;
    MultiAutoCompleteTextView editTag;
    Button btnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_plan_add_setting, container, false);

        editPlanName = view.findViewById(R.id.editPlanName);
        editTag = view.findViewById(R.id.editTag);
        btnNext = view.findViewById(R.id.btnNext);

        final String[] Tags = {"힐링", "온천", "제주", "서울근교", "국내여행", "바람", "맛집"};
        ArrayAdapter<String> TagsAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, Tags);

        editTag.setAdapter(TagsAdapter);
        editTag.setThreshold(1);
        editTag.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        return view;
    }
}
