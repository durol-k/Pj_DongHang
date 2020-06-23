package com.capstone.donghang.community;

import android.os.Bundle;
import android.util.Log;
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
 * 커뮤니티 메인화면 프래그먼트(게시글 리스트보이는 화면)
 */
public class Fragment_Community_MainList extends Fragment {
    private List<Fragment_Community_MainListItem> entireItemList = new ArrayList<>(); // 전체 게시글
    private List<Fragment_Community_MainListItem> listForCategory = new ArrayList<>(); // 카테고리별 게시글
    private DrawerLayout drawerLayout; // 네비게이션 드로어
    private TextView name; // 상단 카테고리 제목 표시 텍스트 뷰
    private MenuItem menuItem; // 카테고리 메뉴 아이템
    private String catName; // 카테고리 이름, 처음에 null값인데, 글쓰기 화면 갔다가 오면 초기화되게 함.
    int catNum = R.id.content_all; // 카테고리 번호, 처음에는 전체글보기로 초기화


    public Fragment_Community_MainList() {
        itemInit();
        listForCategory.addAll(entireItemList);
    }

    public Fragment_Community_MainList(List<Fragment_Community_MainListItem> itemList, int catNum, String catName) {
        entireItemList = itemList;
        this.catName = catName;

        listForCategory.clear();
        if (catNum == R.id.content_all) {
                listForCategory.addAll(entireItemList);
        } else {
            for (int i = 0; i < entireItemList.size(); i++) {
                if (entireItemList.get(i).getCatNum() == catNum)
                    listForCategory.add(entireItemList.get(i));
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.fragment_community_mainlist, container, false);
        drawerLayout = view.findViewById(R.id.community_drawer);
        FloatingActionButton fab = view.findViewById(R.id.community_fab);
        name = view.findViewById(R.id.community_main_category_name);

        if (menuItem != null) // 다른 프래그먼트 갔다가 오면 제목 초기화되는 거 방지
           name.setText(menuItem.getTitle());
         if (catName != null) // 글쓰기화면에서 다시 게시글 리스트화면으로 넘어올 때 제목 초기화 되는 거 방지
            name.setText(catName);

        /* 게시글 리스트 어댑터 설정 */
        RecyclerView recyclerView = view.findViewById(R.id.community_mainlist_recyclerview);
        final Fragment_Community_MainList_RecyclerAdapter recyclerAdapter = new Fragment_Community_MainList_RecyclerAdapter(getContext(), listForCategory);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL)); //구분선

        /*네비게이션 드로어 */
        final NavigationView navigationView = view.findViewById(R.id.community_navi);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.END); // 드로어 닫기

                menuItem = item; //현재 item 정보 저장
                String title = item.getTitle().toString(); // 해당 메뉴 아이템의 제목 저장
                name.setText(title); // 상단 카테고리 제목 설정
                catNum = item.getItemId(); // 해당 카테고리 ID 저장

                listForCategory.clear(); // 리스트 초기화
                if (catNum == R.id.content_all) { // 카테고리 인덱스가 전체글보기면 모든 게시글을 넣어서 출력
                    listForCategory.addAll(entireItemList);
                } else { // 전체글보기가 아니면 전체글에서 해당 카테고리 번호인 것만 추출해서 출력
                    for (int i = 0; i < entireItemList.size(); i++) {
                        if (entireItemList.get(i).getCatNum() == catNum)
                            listForCategory.add(entireItemList.get(i));
                    }
                }
                recyclerAdapter.notifyDataSetChanged(); // 어댑터 갱신
                return true;
            }
        });

        /* 플로팅 버튼 이벤트 (글쓰기 )*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 글쓰기 프래그먼트로 이동 */
                Fragment_Community_Write write_fragment = new Fragment_Community_Write(entireItemList);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_main, write_fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.community_main_toolbar_menu, menu); // 메뉴 인플레이션

        /* 검색 (미완성)*/
        SearchView searchView = (SearchView) menu.findItem(R.id.community_main_search_btn).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색어를 입력해주세요");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.community_main_navigation_btn: //햄버거버튼 클릭 시 드로어 열기
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

    public void itemInit() {
        entireItemList.add(new Fragment_Community_MainListItem("ironman", "16:16", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("superman", "16:15", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("batman", "16:13", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("nosangho", "16:12", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("hiyo", "16:11", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("come", "16:10", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("mjcgood", "16:09", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("imfather", "16:09", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("yours", "16:06", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("goobne", "16:05", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("bbq", "16:04", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("hite", "16:03", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("times", "16:02", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("hello", "16:01", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("codinggood", "16:00", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("covering", "15:57", "여행 후기", "춘천 여행 다녀와서 쓴 후기입니다. 총 여행기간은 3박4일입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:45", "제주도 여행 가실분 구합니다", "2020년 8월2일~8월6일 4박 5일동안 제주도 가실 동행자 구합니다 ~ 쪽지/댓글주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:23", "안녕하세요", "안녕하세요. 명지전문대학교 재학중인 홍길동입니다. 저와 함께 여행 가실 분 구합니다^^", R.drawable.apple, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));

    }
}
