package com.capstone.donghang.Search;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;
import com.capstone.donghang.Search.AfterSearch.FragmentAfterSearch;
import com.capstone.donghang.Search.SelectSearch.FragmentSelectPlan;
import com.capstone.donghang.Search.SelectSearch.FragmentSelectSearch;
import com.capstone.donghang.Search.SelectSearch.FragmentSelectSearchDto;
import com.capstone.donghang.community.Fragment_Community_Write;
import com.google.android.gms.maps.model.LatLng;
import com.capstone.donghang.plan.FragmentPlan;

import java.util.ArrayList;

public class FragmentSearch extends Fragment {
    RecyclerView rcv1,rcv2;
    ArrayList<FragmentSelectSearchDto> select_info = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        setHasOptionsMenu(true);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list1_ = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<Integer> list2_ = new ArrayList<>();
        String[] data1 = {"다대포항","해운대","월미곶","백록담","선유도"};
        String[] data2 = {"강릉1박","붓싼여행기","문화답사기","서울근교나들이","환상의섬"};
        Integer[] data1_ = {R.drawable.dadaepohang1,R.drawable.heumdae1,R.drawable.ulmigot1,R.drawable.bakrokdam1,R.drawable.sunyudo1};
        Integer[] data2_ = {R.drawable.kangonedo1,R.drawable.pusan1,R.drawable.kungju1,R.drawable.inchuone1,R.drawable.jeju1};

        for(int i=0; i<5; i++){
            list1.add(String.format(data1[i]));
            list2.add(String.format(data2[i]));
            list1_.add(data1_[i]);
            list2_.add(data2_[i]);
        }

        rcv1 = view.findViewById(R.id.rcv여행지);
        rcv2 = view.findViewById(R.id.rcv코스);
        rcv1.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcv2.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));

        SearchMainAdapter adt1;
        SearchMainAdapter2 adt2;
        adt1 = new SearchMainAdapter(list1,list1_);
        adt2 = new SearchMainAdapter2(list2,list2_);

        rcv1.setAdapter(adt1);
        rcv2.setAdapter(adt2);



        //다대포항
        ArrayList<Integer> dadaepohang = new ArrayList<>();
        dadaepohang.add(R.drawable.dadaepohang1);
        dadaepohang.add(R.drawable.dadaepohang2);
        dadaepohang.add(R.drawable.dadaepohang3);
        LatLng dadaepoLatLng = new LatLng(35.056564, 128.980268);
        select_info.add(new FragmentSelectSearchDto("다대포항","항구","부산광역시 사하구 다대동에 있는 항구로, 국가어항이다. 1971년 제1종 어항(지금의 국가어항)으로 지정되면서 개발되기 시작하여 1995년 공사를 완료하였다."
                            ,"부산 사하구 다대동","051-144-1248",(float)3.6,dadaepohang,dadaepoLatLng));

        ArrayList<Integer> heumdae = new ArrayList<>();
        heumdae.add(R.drawable.heumdae1);
        heumdae.add(R.drawable.heumdae2);
        heumdae.add(R.drawable.heumdae3);
        LatLng heumdaeLatLng = new LatLng(35.158676, 129.160268);
        select_info.add(new FragmentSelectSearchDto("해운대","해수욕장","해운대해수욕장은 대한민국 부산광역시 해운대구 중동과 우동에 걸쳐서 위치한 해수욕장이다. 모래사장의 총면적은 120,000m2, 길이는 1.5 km, 폭은 70m ~ 90m이다."
                ,"부산 해운대구 우동","051-749-7601",(float)4.3,heumdae,heumdaeLatLng));

        ArrayList<Integer> ulmigot = new ArrayList<>();
        ulmigot.add(R.drawable.ulmigot1);
        ulmigot.add(R.drawable.ulmigot2);
        ulmigot.add(R.drawable.ulmigot3);
        LatLng ulmigotLatLng = new LatLng(36.076766, 129.569037);
        select_info.add(new FragmentSelectSearchDto("월미곶","해돋이 명소","대한민국 내륙에서 가장 해가 먼저 뜨는 곳이다. 포항시의 옛 이름인 '영일(迎日)'[2]이 '해를 맞이한다'는 뜻이기도 하고. 그러나 울산 간절곶이 호미곶보다 우리가 더 빨리 뜬다. 라고 주장해서 포항시와 울산시가 서로 옥신각신 싸우고 있다."
                ,"경상북도 포항시 남구 호미곶면 ","054-270-5855",(float)5.0,ulmigot,ulmigotLatLng));

        ArrayList<Integer> bakrokdam = new ArrayList<>();
        bakrokdam.add(R.drawable.bakrokdam1);
        bakrokdam.add(R.drawable.bakrokdam2);
        bakrokdam.add(R.drawable.bakrokdam3);
        LatLng bakrokdamLatLng = new LatLng(33.362186, 126.533512);
        select_info.add(new FragmentSelectSearchDto("백록담","호수,연못,저수지","해발 고도가 1,947m로 남한에서 가장 높은 산인 한라산의 정상에 위치해 있으며, 면적 약 330,000㎡, 둘레 약 1,720m, 동서길이 약 600m, 남북길이 약 400m, 표고 약 1841.7m, 깊이는 약 108m인 순상화산의 화구호이나, 저수량은 많지 않다."
                ,"제주 서귀포시 토평동 산15-1","064-756-9950",(float)2.3,bakrokdam,bakrokdamLatLng));

        ArrayList<Integer> sunyudo = new ArrayList<>();
        sunyudo.add(R.drawable.sunyudo1);
        sunyudo.add(R.drawable.sunyudo2);
        sunyudo.add(R.drawable.sunyudo3);
        LatLng sunyudoLatLng = new LatLng(35.810775, 126.415946);
        select_info.add(new FragmentSelectSearchDto("선유도","섬","선유도(仙遊島)는 전라북도 군산시 옥도면 선유도리의 섬으로, 고군산군도의 중심지이자 관광지로 유명하다. 면적은 2.13 km², 해안선 길이는 12.8 km이다."
                ,"전북 군산시 옥도면 선유남길 34-22","063-465-5186",(float)1.7,sunyudo,sunyudoLatLng));




        adt1.setOnItemClickListener(new SearchMainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                FragmentSelectSearch fragmentSelectSearch = new FragmentSelectSearch();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_main, fragmentSelectSearch);
//                transaction.addToBackStack(null);
//                transaction.commit();

                Fragment fragment1 = new FragmentSelectSearch(select_info.get(position));
                ((MainActivity)getActivity()).replaceFragment(fragment1);

            }
        });

        adt2.setOnItemClickListener(new SearchMainAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                FragmentSelectPlan fragmentSelectPlan = new FragmentSelectPlan();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_main, fragmentSelectPlan);
//                transaction.addToBackStack(null);
//                transaction.commit();

                Fragment fragment2 = new FragmentSelectPlan();
                ((MainActivity)getActivity()).replaceFragment(fragment2);
            }
        });

        return view;
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

        //검색시 검색창 뜨도록 설정
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Fragment fragment3 = new FragmentAfterSearch(select_info);
                ((MainActivity)getActivity()).replaceFragment(fragment3);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
