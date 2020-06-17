package com.capstone.donghang.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class RecyclerAdapterPlanDetail extends RecyclerView.Adapter<RecyclerAdapterPlanDetail.CustomViewHolder> {

    private ArrayList<ItemPlanDetail> list;

    public RecyclerAdapterPlanDetail(ArrayList<ItemPlanDetail> list) {
        this.list = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView period;
        public CustomViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.itemName);
            this.period = view.findViewById(R.id.itemPeriod);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterPlanDetail.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_detail_recycler_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
