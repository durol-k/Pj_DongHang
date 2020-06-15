package com.capstone.donghang.Search.SelectSearch;

import java.util.ArrayList;

public class Position {
    public String position,image;
    public ArrayList<String> place = new ArrayList<String>();

    public Position(String _position){
        position = _position;
    }
    public String toString(){
        return position;
    }

}
