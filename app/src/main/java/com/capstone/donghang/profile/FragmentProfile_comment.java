package com.capstone.donghang.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
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

public class FragmentProfile_comment extends Fragment {

    ArrayList<CommentData> dataLists;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ProfileCommentAdapter profileCommentAdapter;
    androidx.appcompat.widget.Toolbar toolbar;
    AppCompatActivity activity;
    ImageView backBtn;
    ActionBar actionBar;


    public FragmentProfile_comment(ActionBar actionBar){
        this.actionBar = actionBar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataLists = new ArrayList<>();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle("내가 작성한 댓글");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_profile_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();

        recyclerView = getView().findViewById(R.id.post_recycleView);

        profileCommentAdapter = new ProfileCommentAdapter(dataLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(profileCommentAdapter);

        profileCommentAdapter.addItem("게시글: 경주 불국사를 가야하는 이유", "2020.01.04", "하도 수학여행으로 가봐서 그렇게 재미있던가?");
        profileCommentAdapter.addItem("게시글: 첫 해외여행 썰", "2020.02.11","한번도 해외여행 안 가봤는데 안 힘든가요?");
        profileCommentAdapter.addItem("게시글: 외국어 못해도 해외여행 갈 수 있다", "2020.02.13","제 동생도 일본어 못하지만 일본 잘만 갔다왔습니다.");
        profileCommentAdapter.addItem("게시글: 돈 적게 드는 여행플랜 없을까요?", "2020.04.04","돈을 어떻게 적게 쓸까보다는 여행으로 무엇을 얻을까 생각해보심이");
        profileCommentAdapter.addItem("게시글: 이 어플리케이션 좋은가요?", "2020.06.22","생각보다 쏠쏠해요");



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
