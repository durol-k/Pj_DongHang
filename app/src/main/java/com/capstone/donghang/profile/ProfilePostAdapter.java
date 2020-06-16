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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_post_item, parent,false);
        Holder holder = new Holder(view);




        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostAdapter.Holder holder, int position) {

        int pos = position;
        holder.title.setText(dataLists.get(pos).getPost_title());
        holder.content.setText(dataLists.get(pos).getPost_content());
        //holder.img.setImageResource();

    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title, content;
        ImageView img;


        Holder(View view){
            super(view);
            title = view.findViewById(R.id.post_title);
            content = view.findViewById(R.id.post_content);
            img = view.findViewById(R.id.profile_img);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos =  getAdapterPosition();
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
        return dataLists.size();
    }

    public void addItem(String title, String content){
        dataLists.add(new PostData(title, content));
    }


    /// 리스너
    public interface OnItemClcikListener{
        void onItemClick(View view, int pos);
    }

    private OnItemClcikListener mListener = null;

    public void setOnItemClickListener(OnItemClcikListener listener){
        this.mListener = listener;

    }
}
