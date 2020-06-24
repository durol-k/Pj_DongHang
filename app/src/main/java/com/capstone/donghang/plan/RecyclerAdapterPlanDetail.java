package com.capstone.donghang.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class RecyclerAdapterPlanDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int PARENT = 0;
    public final static int CHILD = 1;

    private ArrayList<ItemPlanDetail> dataSet;

    public RecyclerAdapterPlanDetail(ArrayList<ItemPlanDetail> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;

        if (viewType == PARENT) {
            view = inflater.inflate(R.layout.recycler_item_plan_detail_parent, parent, false);
            return new ParentViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.recycler_item_plan_detail_child, parent, false);
            return new ChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ParentViewHolder)
            ((ParentViewHolder) holder).date.setText(dataSet.get(position).getDate());
        else {
            ((ChildViewHolder) holder).title.setText(dataSet.get(position).getTitle());
            ((ChildViewHolder) holder).address.setText(dataSet.get(position).getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getType();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {
        TextView date;

        public ParentViewHolder(View view) {
            super(view);
            this.date = view.findViewById(R.id.itemDate);
        }
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView address;

        public ChildViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.itemName);
            this.address = view.findViewById(R.id.itemAddress);
        }
    }
}
