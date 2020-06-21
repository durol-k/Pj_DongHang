package com.capstone.donghang.profile;

import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class FragmentProfile_post extends Fragment {

    ArrayList<PostData> dataLists;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ProfilePostAdapter postAdapter;
    AppCompatActivity activity;
    ImageView backBtn;ActionBar actionBar;


    public FragmentProfile_post(ActionBar actionBar){
        this.actionBar = actionBar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dataLists = new ArrayList<>();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle("내가 작성한 게시글");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_profile_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getActivity().setTitle("내가 작성한 게시글");

        recyclerView = getView().findViewById(R.id.post_recycleView);
        postAdapter = new ProfilePostAdapter(dataLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(postAdapter);
        postAdapter.addItem("누구 부산 같이 갈 사람 없소?", "파트너 게시판", "2019.11.11", "2019.11.23.에 누구 시간 비는 사람 없습니까? 나랑 부산 같이 여행 갑시다!! 같이 갈 사람 댓글 부탁!");
        postAdapter.addItem("첫 자전거 여행 썰풀이", "국내게시판", "2019.11.30","자전거 여행 듣기만 해도 돈 안쓴 거 같은 여행같지만 생각보다 비용이 많이 듭니다. 비오면 자건거 녹슬지 않게 옷도 입혀주고");
        postAdapter.addItem("인간은 무엇때문에 살까요?","자유게시판", "2020.01.14", "인간은 무엇 때문에 사는 것입니까? 사랑? 명예? 돈? ");
        postAdapter.addItem("바다에 놀러갈 때 준비","자유게시판", "2020.06.01", "여자든 남자든 간에 바다에 놀러 갈 때 필히 준비해야할 것은 바로바로 썬크림! 되시겠스무니다. 예전에 한번 안 가져갔다가 된장 바른 듯 갈색화");
        postAdapter.addItem("여행을 가야하는 이유", "자유게시판", "2020.06.02","시간이 안 난다는 것은 사실 거짓말입니다. 야헹을 꼭 멀리 갈 필요없이 산책도 하나의 여행이 될 수 있고, 보는 것보다 실제로 경험하는 것");



        super.onActivityCreated(savedInstanceState);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id){

            case android.R.id.home:
                Toast.makeText(getContext(), "백 눌림", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


}
