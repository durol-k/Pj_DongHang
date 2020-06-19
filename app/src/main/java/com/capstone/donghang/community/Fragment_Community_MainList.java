package com.capstone.donghang.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 커뮤니티 메인화면 프래그먼트(게시글 리스트보이는 화면)
 */
public class Fragment_Community_MainList extends Fragment {
    List<Fragment_Community_MainListItem> itemList = new ArrayList<>();
    DrawerLayout drawerLayout;
    FloatingActionButton fab;
    public Fragment_Community_MainList() {
        itemList.add(new Fragment_Community_MainListItem("ironman", "16:16", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon));
        itemList.add(new Fragment_Community_MainListItem("superman", "10:54", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon));
        itemList.add(new Fragment_Community_MainListItem("batman", "01:23", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon));
    }

    public Fragment_Community_MainList(List<Fragment_Community_MainListItem> itemList) { // 글쓰기화면에서 객체 생성시 사용하는 생성자 쓴 글에 대한 정보를 담고 있음
        this.itemList = itemList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.fragment_community_mainlist, container, false);
        drawerLayout = view.findViewById(R.id.community_drawer);
        fab = view.findViewById(R.id.community_fab);

        initData();

        RecyclerView recyclerView = view.findViewById(R.id.community_mainlist_recyclerview);
        Fragment_Community_MainList_RecyclerAdapter recyclerAdapter = new Fragment_Community_MainList_RecyclerAdapter(getContext(),itemList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL)); //구분선

        NavigationView navigationView = view.findViewById(R.id.community_navi);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.END);

                TextView name = view.findViewById(R.id.community_main_category_name);
                name.setText(item.getTitle());

                return true;
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment_Community_Write write_fragment = new Fragment_Community_Write(itemList);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_main, write_fragment);
                transaction.commit();
            }
        });

        return view;
    }

    void initData() {

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.community_main_toolbar_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.community_main_search_btn).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색어를 입력해주세요");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.community_main_navigation_btn:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }
}
