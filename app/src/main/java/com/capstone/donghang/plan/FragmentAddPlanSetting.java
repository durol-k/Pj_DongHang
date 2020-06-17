package com.capstone.donghang.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan_add_setting, container, false);

        EditText editPlanName = root.findViewById(R.id.editPlanName);
        MultiAutoCompleteTextView editTag = root.findViewById(R.id.editTag);
        Button btnNext = root.findViewById(R.id.btnNext);

        String[] Tags = {"힐링", "온천", "제주", "서울근교", "국내여행", "바람", "맛집"};

        ArrayAdapter<String> TagsAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_dropdown_item_1line, Tags);

        editTag.setAdapter(TagsAdapter);
        editTag.setThreshold(1);
        editTag.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        return root;
    }
}
