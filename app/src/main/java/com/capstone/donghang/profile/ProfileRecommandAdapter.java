package com.capstone.donghang.profile;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;

import java.util.ArrayList;

public class ProfileRecommandAdapter extends RecyclerView.Adapter<ProfileRecommandAdapter.Holder>{


    ArrayList<RecommandData> dataLists;



    public ProfileRecommandAdapter(ArrayList<RecommandData> dataLists){
        this.dataLists = dataLists;

    }

    @NonNull
    @Override
    public ProfileRecommandAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_recommand_item, parent,false);
        Holder holder = new Holder(view, context);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileRecommandAdapter.Holder holder, int position) {

        int pos = position;
        holder.name.setText(dataLists.get(pos).getName());
        holder.location.setText(dataLists.get(pos).getLocation());


        //holder.img.setImageResource();

    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name, location;
        LinearLayout places_layout;



        Holder(View view, Context context){
            super(view);

            name = view.findViewById(R.id.user_name);
            location = view.findViewById(R.id.user_location);
            places_layout = view.findViewById(R.id.trip_place_list);
            textview("여행장소1", context, places_layout);
            textview("여행장소2", context, places_layout);
            textview("여행장소3", context, places_layout);


        }
    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public void addItem(String name, String location){
        dataLists.add(new RecommandData(name, location));
    }

    public void textview(String text, Context context, LinearLayout parent){
        //TextView 생성
        TextView view1 = new TextView(context);
        view1.setText(text);
        view1.setTextColor(Color.BLACK);

        //layout_width, layout_height, gravity 설정
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        view1.setLayoutParams(lp);

        //부모 뷰에 추가
        parent.addView(view1);
    }

}
