package com.capstone.donghang.Search;

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

public class SearchMainAdapter extends RecyclerView.Adapter<SearchMainAdapter.ViewHolder> {
    private ArrayList<String> mData =null;
    private ArrayList<Integer> mDataPic =null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        ImageView iv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.horizon_txt);
            iv1 = itemView.findViewById(R.id.horizon_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        //리스너 객체 메서드 호출
                        if(mListener != null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
            });
        }
    }

    SearchMainAdapter(ArrayList<String> list,ArrayList<Integer> listPic){
        mData = list;
        mDataPic = listPic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.horizon_recycler_itmes,parent,false);
        SearchMainAdapter.ViewHolder vh = new SearchMainAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.tv1.setText(text);
        holder.iv1.setImageResource(mDataPic.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener (OnItemClickListener listener){
        this.mListener = listener;
    }


}
