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

    public Fragment_Community_MainList(Fragment_Community_MainListItem post, int catNum, String catName) {
        entireItemList.add(post);
        itemInit();
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
                if (catNum == R.id.content_all) { // 카테고리가 전체글보기면 모든 게시글을 넣어서 출력
                    listForCategory.addAll(entireItemList);
                } else if (catNum == R.id.content_popular) { // 카테고리가 인기게시글이면 조회수 300이상만 출력
                    for( int i = 0 ; i < entireItemList.size(); i++) {
                        if(entireItemList.get(i).getView() >= 300) {
                            listForCategory.add(entireItemList.get(i));
                        }
                    }
                }  else { // 전체글보기가 아니면 전체글에서 해당 카테고리 번호인 것만 추출해서 출력
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
                Fragment_Community_Write write_fragment = new Fragment_Community_Write();
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
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:16", "이용 안내", "이용안내에 대한 공지사항입니다.", null, 30, 0, 50, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:15", "채팅방 개설 안내", "채팅방을 개설할 때 하는 안내사항입니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:13", "어플 이용 안내", "어플 이용 시 주의사항입니다.",null, 2, 0, 15, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin2", "16:12", "사기 조심하세요", "채팅방으로 유도 후 사기치는 사람 있으면 신고해주세요", null, 30, 0, 45, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin3", "16:11", "게시판 이용안내", "게시판에 대한 이용안내입니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:10", "여행 파트너 찾는법", "여행 파트너 찾는 방법에 대한 공지사항입니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:09", "안전한 여행을 하는 방법", "안전하게 하기 위해선 안전하게 여행을 해야합니다. 모두 안전 조심하세요~", null, 30, 0, 65, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:09", "제주도민 필독", "제주도에서 사시는 분들 중에 감귤농장하시는 분 댓글남겨주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin", "16:06", "필독사항", "근래 채팅방에서의 신고가 많이 들어오고 있습니다. 주의해주세요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_notice));
        entireItemList.add(new Fragment_Community_MainListItem("admin2", "16:05", "점검 안내", "6월24일~6월25일 어플 점검이 있겠습니다.", null, 30, 0, 867, R.drawable.community_icon, R.id.content_notice));

        entireItemList.add(new Fragment_Community_MainListItem("bbq", "16:04", "서울 여행 후기", "2박3일동안 서울에 올라가서 여행을 했습니다. 홍대, 신촌, 이태원 모두 아주 좋았습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("hite", "16:03", "제주도 여행 후기", "5박6일간의 여행 후기를 말씀드리겠습니다. 아주 재밌었습니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("times", "16:02", "전라도 맛집 후기", "한식집이었는데 짭짤하지만 감칠맛 도는 맛 좋아하시는 분에게 추천합니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("hello", "16:01", "전주 맛집 소개!", "전주 한옥마을에 있는 비빔밥집이 참 맛있습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("codinggood", "16:00", "부산 밀면 맛집 소개!", "부산에서 유명한 것중 하나가 밀면인데 맛집을 소개하겠습니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("covering", "15:57", "인천 여행 후기", "당일치기로 인천 여행다녀왔습니다. 먹을거리가 많고 즐길거리도 많네요", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:45", "동해 여행 후기입니다", "6월1일~6월3일 2박3일동안 여행 다녀왔습니다. 재밌었습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:23", "포항 여행 후기에요", "포항쪽에는 멋있는 야경이 많고 갈 곳도 참 많네요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:45", "신촌 맛집소개할게요", "신촌에는 맛집이 참 많습니다. 와규 맛집 참 좋습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_review));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:23", "춘천 닭갈비 맛있네요", "춘천 닭갈비 정말 맛있습니다. 한번 먹어보세요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_review));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "15:22", "일본 여행 후기", "7박8일동안 다녀온 일본 여행 후기입니다. 거리가 엄청 깨끗해서 좋았어요", null, 30, 0, 300, R.drawable.community_icon, R.id.content_foreign_review));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:21", "베트남 여행 후기입니다", "5박6일동안 베트남 다녀왔습니다! 먹거리도 많고 문화체험할 것 도 많아서 좋네요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_foreign_review));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:20", "필리핀 여행 후기에요", "값도 싸고 맛있는 음식들이 많은 필리핀에 다녀왔습니다. 4박5일동안 다녀왔네요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_foreign_review));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:19", "미국 뉴욕 여행 후기", "천조국 클라스 맛보고 왔습니다. 힐링 재대로 했습니다", null, 10, 0, 34, R.drawable.community_icon, R.id.content_foreign_review));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:18", "캐나다 여행 후기", "풍경이 엄청 예뻤어요 다른 사람들에게도 추천합니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_foreign_review));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "15:17", "일본 여행 사진", "당일치기로 인천 여행다녀왔습니다. 먹을거리가 많고 즐길거리도 많네요", null, 30, 0, 300, R.drawable.community_icon, R.id.content_travel_picture));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:15", "동해 여행 사진", "6월1일~6월3일 2박3일동안 여행 다녀왔습니다. 재밌었습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_travel_picture));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:13", "신촌 여행 사진입니다.", "포항쪽에는 멋있는 야경이 많고 갈 곳도 참 많네요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_travel_picture));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:11", "부산 여행 사진입니다.", "신촌에는 맛집이 참 많습니다. 와규 맛집 참 좋습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_travel_picture));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:10", "서울 여행 사진이에요", "춘천 닭갈비 정말 맛있습니다. 한번 먹어보세요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_travel_picture));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "15:09", "인천 여행 동행자 구합니다", "당일치기로 아무때나 상관없습니다. 가실분 댓글 남겨주세요", null, 30, 0, 300, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:08", "동해 여행 파트너 구해요!", "6월1일~6월3일 2박3일동안 여행 다녀오실분 구합니다. ", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:07", "춘천 닭갈비 드시러가실분~", "춘천 닭갈비 맛있는 곳 알고 있습니다. 가실 분 ㄱㄱㄱㄱ", null, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:06", "부산 2박3일 놀러가실분", "부산 2박3일동안 힐링여행 하실분 구해요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_domestic_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:05", "가평 빠지 가실분!", "빠지가서 시원하게 빠지실분 구합니다", null, 2, 0, 15, R.drawable.community_icon, R.id.content_domestic_donghang));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "15:04", "일본 여행 가실분!!!", "6박7일 일본 여행 가실분 오사카 후쿠오카 갔다올 예정입니다", null, 30, 0, 300, R.drawable.community_icon, R.id.content_foreign_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:03", "인도 여행 동반자 구해요~~", "딱 7일동안만 다녀올 예정입니다. 같이 가실분 힐링하러갑시다", null, 10, 0, 34, R.drawable.community_icon, R.id.content_foreign_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "15:02", "캐나다 여행 같이 가실분 구해요", "캐나다 2주동안 힐링하러 다녀오실분 구합니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_foreign_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "15:01", "영국 4주동안 갔다오실 분 구합니다!", "영국에서 같이 살면서 여행다니실분 구해요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_foreign_donghang));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:59", "호주 30일 살기 같이 하실분 구해요~", "호주에서 여러 곳 둘러보면서 여행하실분~~~", null, 2, 0, 15, R.drawable.community_icon, R.id.content_foreign_donghang));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:58", "여행 필수품 ", "여행의 필수품을 알려드릴게요. 일단 가방을 챙기세요", null, 30, 0, 300, R.drawable.community_icon, R.id.content_info));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:57", "자전거 여행의 매력", "차가 아닌 자전거를 이용해서 여행다니는 것도 재밌답니다", null, 10, 0, 34, R.drawable.community_icon, R.id.content_info));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:55", "여행시 주의사항", "어두운 골목을 들어가는 것은 자신을 해치는 짓이에요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_info));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:54", "맛집 구별법", "음식점은 많고 맛집이라고 하지만 맛집이 아닌데가 많아서 구별하는 방법을 알려드릴게요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_info));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:53", "여행 상대 고르는법", "자신의 성격과 비슷한지 대화를 좀 하다가 보면 알 수 있어요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_info));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:52", "여행 팁", "여행을 갈 때 너무 무겁게 챙겨서 가면 오히려 더 힘들답니다", null, 30, 0, 300, R.drawable.community_icon, R.id.content_tip));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:51", "길 잃어버렸을 때 팁", "주변 사람의 도움을 빌리세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_tip));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:50", "외국인과 말이 안통할 때 팁", "핸드폰에서 구글번역을 키세요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_tip));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:49", "가다가 사고났을때 팁", "바로 병원을 가시면 됩니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_tip));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:48", "맛집 구별 팁 ", "한번 먹어보세요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_tip));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:47", "여행은 어떻게 하는거죠?", "여행을 한번도 안해봤는데 어떻게하는거죠?", null, 30, 0, 300, R.drawable.community_icon, R.id.content_qna));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:46", "춘천 코스를 어떻게 짜야할까요?", "코스를 짜려고 해봤는데 어떻게 짤지 모르곘네요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_qna));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:45", "여행 코스 짜기가 어렵습니다.", "평소에 안짜보니까 좀 도와주실분!", null, 2, 0, 15, R.drawable.community_icon, R.id.content_qna));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:44", "맛집좀 추천해주세요!", "주변 맛집이랑 지역좀 말해주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_qna));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:43", "엄청난 도움좀 주세요", "저에게 큰 도움을 주실분을 구합니다!", null, 2, 0, 15, R.drawable.community_icon, R.id.content_qna));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:42", "불꽃축제 일정", "여름 불꽃 축제 일정입니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_news));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:41", "국토종주 인원 모집", "국토종주 하실분들은 참고하여 지원해주세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_news));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:40", "한국문화관광공단 이벤트", "한국문화관광공단에서 다음과 같은 이벤트를 진행합니다", null, 2, 0, 15, R.drawable.community_icon, R.id.content_news));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:39", "각종 축제 안내", "불꽃축제, 머드축제, 토마토축제 여러가지 이벤트 안내입니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_news));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:38", "이벤트 안내", "각종 이벤트에 대한 안내입니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_news));


        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:31", "여행 가방 판매", "여행 가방을 팔고있습니다. 빨리 구매하세요", null, 30, 0, 300, R.drawable.community_icon, R.id.content_sale));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:30", "여행 신발 판매", "이 신발을 신으면 어디든지 갈 수 있습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_sale));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:29", "여행 용품 세트 판매", "모든 여행에 필요한 필수품을 구성하였습니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_sale));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:28", "초보자 여행세트 판매", "초보자들은 이 패키지만 있으면 충분합니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_sale));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:27", "여행 옷 판매", "여행할때 뽐낼 옷을 사보세요", null, 2, 0, 15, R.drawable.community_icon, R.id.content_sale));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:26", "거제도 오세요~", "거제도 아주 공기 맑고 좋습니다.", null, 30, 0, 300, R.drawable.community_icon, R.id.content_talk));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:25", "인천 로컬 맛집 소개", "인천에 돈까스가 아주 맛납니다. 다음에 링크 드리겠습니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_talk));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:24", "구리 곱창 맛집 소개합니다", "곱창은 왕십리 다음에 구리입니다. 야채곱창 엄청맛있습니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_talk));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:23", "부산의 로컬 관광지입니다.", "부산에 부산사람들만 아는 관광지가 있습니다. 오세요", null, 10, 0, 34, R.drawable.community_icon, R.id.content_talk));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:22", "제주도 로컬 맛집 소개", "제주도 은갈치 로컬 맛집을 소개하겠습니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_talk));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:21", "뭐해요?", "님들 뭐해요?", null, 30, 0, 23, R.drawable.community_icon, R.id.content_free));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:20", "배고프다ㅠㅠ", "먹을 거 추천좀요!", null, 10, 0, 34, R.drawable.community_icon, R.id.content_free));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:19", "학교가기싫네요", "2학기에 학교 가기가 싫습니다!", null, 2, 0, 15, R.drawable.community_icon, R.id.content_free));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:18", "뻘글", "뻘글입니다.", null, 10, 0, 34, R.drawable.community_icon, R.id.content_free));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:17", "현타옵니다", "현자타입이 아주 오지게 왔습니다.", null, 2, 0, 15, R.drawable.community_icon, R.id.content_free));

        entireItemList.add(new Fragment_Community_MainListItem("covering", "14:16", "채팅방에 관한 질문", "채팅방에관한 질문인데 채팅을 어떻게 하는거죠?", null, 30, 0, 1, R.drawable.community_icon, R.id.content_request));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:15", "여행 파트에 대한 질문입니다.", "여행 파트너가 뜨질 않는데 해결좀해주세요", null, 10, 0, 1, R.drawable.community_icon, R.id.content_request));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:14", "관리자님 질문좀요", "관리자님은 몇살이시죠?", null, 2, 0, 11, R.drawable.community_icon, R.id.content_request));
        entireItemList.add(new Fragment_Community_MainListItem("sorryman", "14:13", "질문좀 할게요", "질문좀 할게요. 질문좀 해볼게요", null, 10, 0, 14, R.drawable.community_icon, R.id.content_request));
        entireItemList.add(new Fragment_Community_MainListItem("kikkki", "14:12", "아니 문제가 뭐냐면요", "코로나 때매 여행을 못가는데 어떡하죠?", null, 2, 0, 15, R.drawable.community_icon, R.id.content_request));



    }
}
