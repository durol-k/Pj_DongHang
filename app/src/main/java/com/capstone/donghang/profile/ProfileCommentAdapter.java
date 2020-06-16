package com.capstone.donghang.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class ProfileCommentAdapter extends RecyclerView.Adapter<ProfileCommentAdapter.Holder>{


    ArrayList<CommentData> dataLists;



    public ProfileCommentAdapter(ArrayList<CommentData> dataLists){
        this.dataLists = dataLists;

    }

    @NonNull
    @Override
    public ProfileCommentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_comment_item, parent,false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileCommentAdapter.Holder holder, int position) {

        int pos = position;
        holder.title.setText(dataLists.get(pos).getPost_title());
        holder.comment.setText(dataLists.get(pos).getComment_content());

    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title, comment;



        Holder(View view){
            super(view);
            title = view.findViewById(R.id.post_title);
            comment = view.findViewById(R.id.comment_content);

        }
    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public void addItem(String title, String content){
        dataLists.add(new CommentData(title, content));
    }


}
