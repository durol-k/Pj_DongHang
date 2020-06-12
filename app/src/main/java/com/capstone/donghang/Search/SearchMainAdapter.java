package com.capstone.donghang.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class SearchMainAdapter extends RecyclerView.Adapter<SearchMainAdapter.ViewHolder> {
    private ArrayList<String> mData =null;
    private ArrayList<String> mDataPic =null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        ImageView iv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.horizon_txt);
            iv1 = itemView.findViewById(R.id.horizon_icon);
        }
    }

    SearchMainAdapter(ArrayList<String> list,ArrayList<String> listPic){
        mData = list;
        mDataPic = listPic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.horizon_recycler_itmes,parent,false);
        SearchMainAdapter.ViewHolder vh = new SearchMainAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.tv1.setText(text);
        holder.iv1.setImageResource(R.drawable.ic_calendar_material_design);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




}
