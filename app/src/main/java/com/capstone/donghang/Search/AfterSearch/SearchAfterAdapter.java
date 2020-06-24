package com.capstone.donghang.Search.AfterSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;
import com.capstone.donghang.Search.SearchMainAdapter;

import java.util.ArrayList;

public class SearchAfterAdapter extends RecyclerView.Adapter<SearchAfterAdapter.ViewHolder> {
    private ArrayList<String> mData = null;
    private ArrayList<String> mData2 = null;
    private ArrayList<String[]> mDataTag = null;
    private ArrayList<Integer> mDataPic = null;


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv1;
        TextView tvName,tvLoc,tvTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv1 = itemView.findViewById(R.id.ivSA);
            tvName = itemView.findViewById(R.id.tvSAName);
            tvLoc = itemView.findViewById(R.id.tvSALocation);
            tvTag = itemView.findViewById(R.id.tvSATag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        //리스너 객체 메서드 호출
                        if(mListener != null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
            });
        }
    }

    SearchAfterAdapter(ArrayList<String> list,ArrayList<String> list2,ArrayList<String[]> tagList, ArrayList<Integer> listPic){
        mData = list;
        mData2 = list2;
        mDataPic = listPic;
        mDataTag = tagList;
        mDataPic = listPic;
    }

    @NonNull
    @Override
    public SearchAfterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_search_after,parent,false);
        SearchAfterAdapter.ViewHolder vh = new SearchAfterAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAfterAdapter.ViewHolder holder, int position) {
        String text = mData.get(position);
        String text2 = mData2.get(position);

        holder.tvName.setText(text);
        holder.tvLoc.setText(text2);

        if (mDataTag == null){
            holder.tvTag.setVisibility(View.INVISIBLE);
        }else {
            holder.tvTag.setVisibility(View.VISIBLE);


            String textTag[] = mDataTag.get(position);
            holder.tvTag.setText("");
            for (int i = 1; i < textTag.length; i++) {
                holder.tvTag.setText(holder.tvTag.getText()+" #"+textTag[i]);

            }
        }

        holder.iv1.setImageResource(mDataPic.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private SearchMainAdapter.OnItemClickListener mListener = null;

    public void setOnItemClickListener (SearchMainAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }
}
