package com.capstone.donghang.Search.AfterSearch;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;
import com.capstone.donghang.Search.SearchMainAdapter;
import com.capstone.donghang.Search.SelectSearch.FragmentSelectPlan;
import com.capstone.donghang.Search.SelectSearch.FragmentSelectSearch;
import com.capstone.donghang.Search.SelectSearch.FragmentSelectSearchDto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FragmentAfterSearch extends Fragment {
    TabLayout tabLayout;
    RecyclerView rcv;

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list1_= new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list2_= new ArrayList<>();
    ArrayList<Integer> listPic1 = new ArrayList<>();
    ArrayList<Integer> listPic2 = new ArrayList<>();
    ArrayList<String[]> listTag = new ArrayList<>();
    SearchAfterAdapter adt;
    ArrayList<FragmentSelectSearchDto> select_info;
    int pos = 0;
    Fragment fragment1;

    public FragmentAfterSearch(ArrayList<FragmentSelectSearchDto> _select_info){
        select_info = _select_info;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_after, container, false);

        setHasOptionsMenu(true);

        tabLayout = view.findViewById(R.id.tab_layout);
        rcv = view.findViewById(R.id.rcvTab);

        String[] data1 = {"다대포항","해운대","월미곶","백록담","선유도"};
        String[] data1_= {"부산,경상","부산,경상","인천,경기","제주","서울,경기"};
        String[] data2 = {"강릉1박","붓싼여행기","문화답사기","서울근교나들이","환상의섬"};
        String[] data2_ = {"강원도","부산","경주","인천","제주"};
        String[][] tags = {{"힐링","휴식","1박2일"},{"2박3일"},{"문화답사","전통"},{"서울근교","당일치기"},{"한라산"}};
        Integer[] data1_pic = {R.drawable.dadaepohang1,R.drawable.heumdae1,R.drawable.ulmigot1,R.drawable.bakrokdam1,R.drawable.sunyudo1};
        Integer[] data2_pic = {R.drawable.kangonedo1,R.drawable.pusan1,R.drawable.kungju1,R.drawable.inchuone1,R.drawable.jeju1};


        for(int i=0; i<5; i++){
            list1.add(String.format(data1[i]));
            list1_.add(String.format(data1_[i]));
            list2.add(String.format(data2[i]));
            list2_.add(String.format(data2_[i]));
            listTag.add(tags[i]);
            listPic1.add(data1_pic[i]);
            listPic2.add(data2_pic[i]);

        }

        rcv.setLayoutManager(new LinearLayoutManager(view.getContext()));


        adt = new SearchAfterAdapter(list1, list1_, null, listPic1);
        rcv.setAdapter(adt);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택
                pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //선택되지 않음
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //선택된 탭을 다시 누름
           }
        });

        adt.setOnItemClickListener(new SearchMainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch(pos){
                    case 0 :
                        fragment1 = new FragmentSelectSearch(select_info.get(position));
                        ((MainActivity)getActivity()).replaceFragment(fragment1);
                        break;
                    case 1 :
                        fragment1 = new FragmentSelectPlan();
                        ((MainActivity)getActivity()).replaceFragment(fragment1);
                        break;
                }
            }
        });


        return view;
    }



    private void changeView(int index){
        switch(index){
            case 0 :
                adt = new SearchAfterAdapter(list1,list1_,null,listPic1);
                rcv.setAdapter(adt);
                break;
            case 1 :

                adt = new SearchAfterAdapter(list2,list2_,listTag,listPic2);
                rcv.setAdapter(adt);
                break;
        }
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

        MenuItem searchItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("검색어를 입력하세요");


    }
}
