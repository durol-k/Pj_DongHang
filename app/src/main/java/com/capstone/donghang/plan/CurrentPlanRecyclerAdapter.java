package com.capstone.donghang.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class CurrentPlanRecyclerAdapter extends RecyclerView.Adapter<CurrentPlanRecyclerAdapter.CustomViewHolder> {
    private ArrayList<String> dataSet;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView place;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.place = itemView.findViewById(R.id.current_plan_recycler_item);
        }
    }

    public CurrentPlanRecyclerAdapter(ArrayList<String> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CurrentPlanRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currentplan_recycler_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentPlanRecyclerAdapter.CustomViewHolder holder, int position) {
        holder.place.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
