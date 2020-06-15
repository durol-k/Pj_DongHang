package com.capstone.donghang.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Community_ItemInfo extends Fragment {
        private Context context;
        private int icon,viewCount;
        private String id,time,title,content;

        TextView titleTv, idTv, timeTv, viewCountTv, contentTv;
        ImageView iconView;

    public Fragment_Community_ItemInfo(Context context, int icon, int viewCount, String id, String time, String title, String content) {
        this.context = context;
        this.icon = icon;
        this.viewCount = viewCount;
        this.id = id;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_item_info, container, false);
        titleTv = view.findViewById(R.id.community_detail_title);
        iconView = view.findViewById(R.id.community_detail_icon);
        idTv = view.findViewById(R.id.community_detail_nickname);
        timeTv = view.findViewById(R.id.community_detail_time);
        contentTv = view.findViewById(R.id.community_detail_content);
        viewCountTv = view.findViewById(R.id.community_detail_viewCount);

        titleTv.setText(title);
        iconView.setImageResource(icon);
        idTv.setText(id);
        timeTv.setText(time);
        contentTv.setText(content);
        viewCountTv.setText(String.valueOf(viewCount));


        ArrayList<Fragment_Community_Comment_Item> commentList = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.community_detail_commentRecyclerView);

        commentList.add(new Fragment_Community_Comment_Item(id, time, content, icon));
        commentList.add(new Fragment_Community_Comment_Item(id, time, content, icon));
        commentList.add(new Fragment_Community_Comment_Item(id, time, content, icon));

        Fragment_Community_Comment_RecyclerAdapter commentRecyclerAdapter = new Fragment_Community_Comment_RecyclerAdapter(getContext(),commentList);
        recyclerView.setAdapter(commentRecyclerAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().remove(Fragment_Community_ItemInfo.this);
    }

}
