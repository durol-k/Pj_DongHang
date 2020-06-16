package com.capstone.donghang.community;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;

import java.util.List;

public class Fragment_Community_MainList_RecyclerAdapter extends RecyclerView.Adapter<Fragment_Community_MainList_RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Fragment_Community_MainListItem> itemList;

    public Fragment_Community_MainList_RecyclerAdapter(Context context, List<Fragment_Community_MainListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_community_mainitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fragment_Community_MainListItem item = itemList.get(position);
        String id = item.getId();
        String time = item.getTime();
        String title = item.getTitle();
        String content = item.getContent();
        int img = item.getImg();
        int like = item.getLike();
        int comment = item.getComment();
        int viewCount = item.getView();
        int icon = item.getIcon();

        holder.idTv.setText(id);
        holder.timeTv.setText(time);
        holder.titleTv.setText(title);
        holder.contentTv.setText(content);
        holder.imgView.setImageResource(img);
        holder.likeTv.setText(String.valueOf(like));
        holder.commentTv.setText(String.valueOf(comment));
        holder.viewCountTv.setText(String.valueOf(viewCount));
        holder.iconView.setImageResource(icon);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTv, timeTv, titleTv, contentTv, likeTv, commentTv, viewCountTv;
        ImageView imgView, iconView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.community_id);
            timeTv = itemView.findViewById(R.id.community_time);
            titleTv = itemView.findViewById(R.id.community_title);
            contentTv = itemView.findViewById(R.id.community_content);
            imgView = itemView.findViewById(R.id.community_image);
            likeTv = itemView.findViewById(R.id.community_mainitem_likeCount);
            commentTv = itemView.findViewById(R.id.community_mainitem_commentCount);
            viewCountTv = itemView.findViewById(R.id.community_mainitem_viewCount);
            iconView = itemView.findViewById(R.id.community_icon);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition();
//                    int icon = itemList.get(pos).getIcon();
//                    int viewCount = itemList.get(pos).getView();
//                    String id = itemList.get(pos).getId();
//                    String time = itemList.get(pos).getTime();
//                    String title = itemList.get(pos).getTitle();
//                    String content = itemList.get(pos).getContent();
//
//                    Fragment_Community_ItemInfo fcd = new Fragment_Community_ItemInfo(context, icon, viewCount, id, time, title, content);
//                    FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_main, fcd);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }
//            });
        }
    }
}
