package com.capstone.donghang.profile;

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

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.Holder>{


    ArrayList<PostData> dataLists;



    public ProfilePostAdapter(ArrayList<PostData> dataLists){
        this.dataLists = dataLists;

    }

    @NonNull
    @Override
    public ProfilePostAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_post_item2, parent,false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostAdapter.Holder holder, int position) {

        int pos = position;
        holder.title.setText(dataLists.get(pos).getPost_title());
        holder.type.setText(dataLists.get(pos).type);
        holder.date.setText(dataLists.get(pos).write_date);
        holder.content.setText(dataLists.get(pos).getPost_content());
        //holder.img.setImageResource();

    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title, type, date, content;
        //ImageView img;


        Holder(View view){
            super(view);
            title = view.findViewById(R.id.post_title);
            type = view.findViewById(R.id.profile_post_type);
            date = view.findViewById(R.id.profile_post_date);
            content = view.findViewById(R.id.post_content);



        }
    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public void addItem(String title, String content){
        dataLists.add(new PostData(title, content));
    }

    public void addItem(String title, String type, String date, String content){
        dataLists.add(new PostData(title, type, date, content));
    }




}
