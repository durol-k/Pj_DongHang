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
        holder.name.setText(dataLists.get(pos).getName()); // 추천인 이름
        holder.location.setText(dataLists.get(pos).getLocation()); // 추천인의 사는 장소
        holder.img.setImageResource(dataLists.get(pos).getImageResource());

    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name, location;
        LinearLayout places_layout; // 동적인 텍스트 뷰를 넣기 위한 레이아웃



        Holder(View view, Context context){
            super(view);

            name = view.findViewById(R.id.user_name);
            location = view.findViewById(R.id.user_location);
            places_layout = view.findViewById(R.id.trip_place_list);
            img = view.findViewById(R.id.profile_recommand_image);

            textview(dataLists.get(0).locations.get(0), context, places_layout);
            textview(dataLists.get(0).locations.get(1), context, places_layout);
            textview(dataLists.get(0).locations.get(2), context, places_layout); // 이후 반복문으로 바꾸어 동적으로 텍스트 뷰 추가


        }
    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public void addItem(int image, String name, String place, ArrayList<String> location){
        dataLists.add(new RecommandData(image, name, place, location));
    }

    public void textview(String text, Context context, LinearLayout parent){
        //TextView 생성
        TextView view1 = new TextView(context);
        view1.setText(text);
        view1.setTextSize(20);

        //layout_width, layout_height, gravity 설정
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        view1.setLayoutParams(lp);

        //부모 뷰에 추가
        parent.addView(view1);
    }

}
