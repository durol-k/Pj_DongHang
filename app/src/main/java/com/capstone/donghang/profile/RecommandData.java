package com.capstone.donghang.profile;

import java.util.ArrayList;

public class RecommandData {

    int userId, getUserId2;
    String name, place, startDate, endDate, location1;
    int imageResource;
    ArrayList<String> locations;

    RecommandData(String name, String location){

        this.name = name;
        this.location1 = location;


    }

    RecommandData(int imageResource, String name, String place, ArrayList<String> location){

        this.imageResource = imageResource;
        this.name = name;
        this.place = place;
        this.locations = location;

        //startDate = start;
        //endDate = end;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location1;
    }

    public String getPlace() {
        return place;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndData() {
        return endDate;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String display_location_date(int pos){
        String output =  locations.get(pos);

        return output;
    }
    public void addItem(String location_data){
        locations.add(location_data);
    }

}

