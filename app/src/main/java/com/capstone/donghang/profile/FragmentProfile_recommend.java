package com.capstone.donghang.profile;

import android.os.Bundle;
import android.os.TokenWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.profile.FragmentProfile;
import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentProfile_recommend extends Fragment {

    ArrayList<RecommandData> dataLists;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ProfileRecommandAdapter recommandAdapter;
    AppCompatActivity activity;
    ActionBar actionBar;
    ArrayList<ArrayList<String>> recommand_user_locations;

    public FragmentProfile_recommend(ActionBar actionBar){
        this.actionBar = actionBar;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        dataLists = new ArrayList<>();
        recommand_user_locations = new ArrayList<>();

        return inflater.inflate(R.layout.fragment_profile_recommend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();
        activity.setTitle("추천 동행자");

        recyclerView = getView().findViewById(R.id.post_recycleView);

        recommandAdapter = new ProfileRecommandAdapter(dataLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recommandAdapter);

        //명분의 여행지 저장
        recommand_user_locations.add(new ArrayList<String>());


        recommand_user_locations.get(0).add("- 해운대 (2020.06.25~2020.06.27)");
        recommand_user_locations.get(0).add("- 국제시장 (2020.06.25~2020.06.27)");
        recommand_user_locations.get(0).add("- 광안대교 (2020.06.25~2020.06.27)");

        recommandAdapter.addItem(R.drawable.rc1, "비행기여행조아","서울", recommand_user_locations.get(0));
        recommandAdapter.addItem(R.drawable.rc2,"사랑해여행", "대전", recommand_user_locations.get(0));
        recommandAdapter.addItem(R.drawable.rc3,"가방하나피크닉", "광주",recommand_user_locations.get(0));
        recommandAdapter.addItem(R.drawable.rc4,"여행의숲", "부산",recommand_user_locations.get(0));




        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_recommand_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id){

            case android.R.id.home:
                Toast.makeText(getContext(), "백 눌림", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
                return true;
            case R.id.partner_setting :
                Toast.makeText(getContext(), "파트너 설정 눌림", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
