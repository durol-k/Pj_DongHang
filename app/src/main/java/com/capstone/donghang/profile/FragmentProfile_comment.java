package com.capstone.donghang.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
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

    ArrayList<CommentData> dataLists = new ArrayList<>();
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ProfileCommentAdapter profileCommentAdapter;
    androidx.appcompat.widget.Toolbar toolbar;
    AppCompatActivity activity;
    ImageView backBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();

        recyclerView = getView().findViewById(R.id.post_recycleView);

        profileCommentAdapter = new ProfileCommentAdapter(dataLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(profileCommentAdapter);

        profileCommentAdapter.addItem("게시글1", "댓글1");
        profileCommentAdapter.addItem("게시글2", "댓글2");
        profileCommentAdapter.addItem("게시글3", "댓글3");
        profileCommentAdapter.addItem("게시글4", "댓글4");
        profileCommentAdapter.addItem("게시글5", "댓글5");
        profileCommentAdapter.addItem("게시글6", "댓글6");
        profileCommentAdapter.addItem("게시글7", "댓글7");


        super.onActivityCreated(savedInstanceState);
    }
}
