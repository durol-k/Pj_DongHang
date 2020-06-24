package com.capstone.donghang.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.Holder>{


    ArrayList<String> title_list;
    Context display;


    public ProfileAdapter(ArrayList<String> title_list){
        this.title_list = title_list;
        this.display = display;
    }

    @NonNull
    @Override
    public ProfileAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_main_item, parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.Holder holder, int position) {

        int pos = position;
        holder.title.setText(title_list.get(pos));

    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title;

        Holder(View view){
            super(view);
            title = view.findViewById(R.id.profile_item_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(v, pos);
                        }

                    }
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return title_list.size();
    }

    public void addItem(String title){
        title_list.add(title);
    }

    /// 리스너
    public interface OnItemClcikListener{
        void onItemClick(View view, int pos);
    }

    private ProfileAdapter.OnItemClcikListener mListener = null;

    public void setOnItemClickListener(ProfileAdapter.OnItemClcikListener listener){
        this.mListener = listener;

    }


}
