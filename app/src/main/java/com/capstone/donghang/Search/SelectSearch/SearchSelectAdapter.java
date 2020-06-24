package com.capstone.donghang.Search.SelectSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;
import com.capstone.donghang.Search.AfterSearch.SearchAfterAdapter;

import java.util.ArrayList;

public class SearchSelectAdapter extends RecyclerView.Adapter<SearchSelectAdapter.ViewHolder> {
    private ArrayList<Integer> mData = null;

    public SearchSelectAdapter (ArrayList<Integer> list){
        mData = list;
    }

    @NonNull
    @Override
    public SearchSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.horizon_recycler_itmes,parent,false);
        SearchSelectAdapter.ViewHolder vh = new SearchSelectAdapter.ViewHolder(view);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv.setVisibility(View.GONE);
        holder.iv.setImageResource(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.horizon_txt);
            iv = itemView.findViewById(R.id.horizon_icon);

        }
    }


}
