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

/**
 댓글 어댑터
 */
public class Fragment_Community_Comment_RecyclerAdapter extends RecyclerView.Adapter<Fragment_Community_Comment_RecyclerAdapter.ViewHolder> {
    Context context;
    List<Fragment_Community_Comment_Item> commentList;

    public Fragment_Community_Comment_RecyclerAdapter(Context context, List<Fragment_Community_Comment_Item> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_community_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fragment_Community_Comment_Item item = commentList.get(position);
        String id = item.getId();
        String time = item.getTime();
        String content = item.getContent();
        int icon = item.getIcon();

        holder.id.setText(id);
        holder.time.setText(time);
        holder.content.setText(content);
        holder.icon.setImageResource(icon);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,time,content;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.community_comment_icon);
            id = itemView.findViewById(R.id.community_comment_nickname);
            content = itemView.findViewById(R.id.community_comment_content);
            time = itemView.findViewById(R.id.community_comment_time);

        }
    }
}
