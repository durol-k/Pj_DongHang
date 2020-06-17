package com.capstone.donghang.plan;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.capstone.donghang.R;

import java.util.ArrayList;

public class RecyclerAdapterPlanList extends RecyclerView.Adapter<RecyclerAdapterPlanList.CustomViewHolder> { // 1-1. 일정리스트 일정 리사이클러 어댑터
    private Context context;
    private ArrayList<ItemPlan> dataSet;

    RecyclerAdapterPlanList(ArrayList<ItemPlan> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerAdapterPlanList.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.planlist_recycler_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        String title = dataSet.get(position).getTitle();
        String period = dataSet.get(position).getPeriod();
        ArrayList<String> tags = dataSet.get(position).getTags();

        holder.title.setText(title);
        holder.period.setText(period);
        for (String tag : tags) {
            TextView tvTag = new TextView(context);
            tvTag.setText(tag);
            tvTag.setTextSize(16);
            tvTag.setTextColor(Color.BLUE);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4, 0, 4, 0);
            tvTag.setLayoutParams(layoutParams);

            holder.tagLayout.addView(tvTag);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView period;
        LinearLayout tagLayout;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.planlist_recycler_item_title);
            this.period = itemView.findViewById(R.id.planlist_recycler_item_period);
            this.tagLayout = itemView.findViewById(R.id.planlist_recycler_item_tagLayout);
        }
    }
}