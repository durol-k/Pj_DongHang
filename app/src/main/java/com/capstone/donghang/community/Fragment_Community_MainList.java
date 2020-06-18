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
    List<Fragment_Community_MainListItem> itemList;
    DrawerLayout drawerLayout;
    FloatingActionButton fab;
    ArrayList<String> writed_item;
    public Fragment_Community_MainList() {

    }

    public Fragment_Community_MainList(ArrayList<String> writed_item) {
        this.writed_item = writed_item;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        itemList = new ArrayList<>();
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

                Fragment_Community_Write write_fragment = new Fragment_Community_Write();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_main, write_fragment);
                transaction.commit();
            }
        });

        return view;
    }

    void initData() {
        String str = "명지전문대명지전문대명지전문대명지전문대명지전문대명지전문대명지전문대";
        if ( writed_item !=null)
          itemList.add(new Fragment_Community_MainListItem("id123", "1111", writed_item.get(2), writed_item.get(3), null, 0, 0, 0, R.drawable.community_icon));

         itemList.add(new Fragment_Community_MainListItem("id" , "00:00", "제목",str, R.drawable.apple,0,0,0, R.drawable.community_icon));

        for(int i = 0 ; i < 10 ; i ++) {
            itemList.add(new Fragment_Community_MainListItem("id" + i, "00:00", "제목" + i, "내용" + i,null,i,i,i, R.drawable.community_icon));
        }
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
