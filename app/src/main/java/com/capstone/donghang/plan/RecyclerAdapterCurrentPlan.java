package com.capstone.donghang.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class RecyclerAdapterCurrentPlan extends RecyclerView.Adapter<RecyclerAdapterCurrentPlan.CustomViewHolder> { // 1-1. 일정리스트 현재 일정 리사이클러 어댑터
    private ArrayList<ItemCurrentPlan> dataSet;

    public RecyclerAdapterCurrentPlan(ArrayList<ItemCurrentPlan> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerAdapterCurrentPlan.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currentplan_recycler_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCurrentPlan.CustomViewHolder holder, int position) {
        String place = dataSet.get(position).getPlace();
        holder.place.setText(place);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView place;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.place = itemView.findViewById(R.id.current_plan_recycler_item);
        }
    }
}
