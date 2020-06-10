package com.capstone.donghang.plan;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class PlanListRecyclerAdapter extends RecyclerView.Adapter<PlanListRecyclerAdapter.CustomViewHolder> {
    private ArrayList<ArrayList<String>> dataSet;
    Context context;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        LinearLayout tagLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.planlist_recycler_item_title);
            this.date = itemView.findViewById(R.id.planlist_recycler_item_date);
            this.tagLayout = itemView.findViewById(R.id.planlist_recycler_item_tagLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public PlanListRecyclerAdapter(ArrayList<ArrayList<String>> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public PlanListRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.planlist_recycler_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.title.setText(dataSet.get(position).get(0));
        holder.date.setText(dataSet.get(position).get(1));
        for (int i = 2; i < dataSet.get(position).size(); i++) {
            TextView tvTag = new TextView(context);
            tvTag.setText(dataSet.get(position).get(i));
            tvTag.setTextSize(16);
            tvTag.setTextColor(Color.BLUE);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4,0,4,0);
            tvTag.setLayoutParams(layoutParams);

            //부모 뷰에 추가
            holder.tagLayout.addView(tvTag);
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}