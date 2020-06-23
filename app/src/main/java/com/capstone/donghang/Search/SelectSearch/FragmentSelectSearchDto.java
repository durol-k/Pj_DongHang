package com.capstone.donghang.Search.SelectSearch;

import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragmentSelectSearchDto {
    private String name,sort,content,address,telnum;
    private float rating;
    private ArrayList<Integer> image;
    private LatLng mapMark;

    public FragmentSelectSearchDto (String _name,String _sort, String _content, String _address, String _telnum, float _rating, ArrayList<Integer> _image, LatLng _mapMark){
        name = _name;
        sort = _sort;
        content =_content;
        address = _address;
        telnum = _telnum;
        rating = _rating;
        image = _image;
        mapMark = _mapMark;
    }

    public ArrayList<Integer> getImage(){return image;}
    public String getName(){return name;}
    public String getSort(){return sort;}
    public String getContent(){return content;}
    public String getAddress(){return address;}
    public String getTelnum(){return telnum;}
    public float getRating(){return rating;}
    public LatLng getMapMark(){return mapMark;}

    public void setName(String _name){name = _name;}
    public void setSort(String _sort){sort = _sort;}
    public void setContent(String _content){content = _content;}
    public void setAddress(String _address){address = _address;}
    public void setTelnum(String _telnum){telnum = _telnum;}
    public void setRating(float _rating){rating = _rating;}
    public void setMapMark(LatLng _mapMark){mapMark = _mapMark;}
    public void setImage(ArrayList<Integer> _image){image = _image;}
}

