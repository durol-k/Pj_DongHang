package com.capstone.donghang.Search.SelectSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstone.donghang.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<Position> position;
    private LayoutInflater inflater;


    public ExpandableAdapter(Context _mContext, ArrayList<Position> _position){
        mContext = _mContext;
        position = _position;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return position.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return position.get(groupPosition).place.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return position.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return position.get(groupPosition).place.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.plan_expan_parent,null);
        }

        //포지션 가져오기
        Position position = (Position) getGroup(groupPosition);

        String positionName = position.position;

        TextView textView = convertView.findViewById(R.id.tvPEP);
        textView.setText(positionName+"일차");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.plan_expan_child,null);
        }

        String child = (String) getChild(groupPosition, childPosition);

        TextView tvPlace = convertView.findViewById(R.id.tvPECPlace);
        TextView tvAddress = convertView.findViewById(R.id.tvPECAddress);
        ImageView img = convertView.findViewById(R.id.ivPEC);

        tvPlace.setText(child);
        tvAddress.setText(child);

        String positionName = (String) getGroup(groupPosition).toString();
        img.setImageResource(R.drawable.ic_calendar_material_design);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
