package com.capstone.donghang.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 각 게시글 상세보기 프래그먼트
 */
public class Fragment_Community_DetailInfo extends Fragment {
        private Context context;
        private int icon,viewCount,img;
        private String id,time,title,content;
        private ArrayList<Fragment_Community_Comment_Item> commentList;
        TextView titleTv, idTv, timeTv, viewCountTv, contentTv;
        ImageView iconView,imageView;
        EditText commentEt;
        Button insertComment;

    public Fragment_Community_DetailInfo(Context context, int icon, int viewCount, String id, String time, String title, String content, int img) {
        this.context = context;
        this.icon = icon;
        this.viewCount = viewCount;
        this.id = id;
        this.time = time;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_detail_info, container, false);
        titleTv = view.findViewById(R.id.community_detail_title);
        iconView = view.findViewById(R.id.community_detail_icon);
        idTv = view.findViewById(R.id.community_detail_nickname);
        timeTv = view.findViewById(R.id.community_detail_time);
        contentTv = view.findViewById(R.id.community_detail_content);
        viewCountTv = view.findViewById(R.id.community_detail_viewCount);
        imageView = view.findViewById(R.id.community_detail_imageView);
        insertComment = view.findViewById(R.id.community_detail_comment_insertBtn);
        commentEt = view.findViewById(R.id.community_detail_writeComment);

        titleTv.setText(title);
        iconView.setImageResource(icon);
        idTv.setText(id);
        timeTv.setText(time);
        contentTv.setText(content);
        viewCountTv.setText(String.valueOf(viewCount));
        if(img !=0) imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(img);

        commentList = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.community_detail_commentRecyclerView);



        final Fragment_Community_Comment_RecyclerAdapter commentRecyclerAdapter = new Fragment_Community_Comment_RecyclerAdapter(getContext(),commentList);
        recyclerView.setAdapter(commentRecyclerAdapter);

        insertComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = commentEt.getText().toString();
                Date date = new Date();
                SimpleDateFormat time = new SimpleDateFormat("hh:mm");

                commentList.add(new Fragment_Community_Comment_Item(id, time.format(date), text, icon));
                commentRecyclerAdapter.notifyDataSetChanged();
            }
        });


        return view;
    }
}
