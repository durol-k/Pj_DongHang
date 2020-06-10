package com.capstone.donghang.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.List;

public class Community_RecyclerAdapter extends RecyclerView.Adapter<Community_RecyclerAdapter.ViewHolder> {
    Context context;
    List<Community_Item> itemList;

    public Community_RecyclerAdapter(Context context, List<Community_Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_community_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Community_Item item = itemList.get(position);
        String id = item.getId();
        String time = item.getTime();
        String title = item.getTitle();
        String content = item.getContent();
        Integer img = item.getImg();
        int like = item.getLike();
        int comment = item.getComment();
        int view = item.getView();

        holder.id.setText(id);
        holder.time.setText(time);
        holder.title.setText(title);
        holder.content.setText(content);
        holder.imgView.setImageResource(img);
        holder.like.setText(String.valueOf(like));
        holder.comment.setText(String.valueOf(comment));
        holder.view.setText(String.valueOf(view));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,time,title,content,like,comment,view;
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.community_id);
            time = itemView.findViewById(R.id.community_time);
            title = itemView.findViewById(R.id.community_title);
            content = itemView.findViewById(R.id.community_content);
            imgView = itemView.findViewById(R.id.community_image);
            like = itemView.findViewById(R.id.community_like);
            comment = itemView.findViewById(R.id.community_comment);
            view = itemView.findViewById(R.id.community_view);
        }
    }
}
