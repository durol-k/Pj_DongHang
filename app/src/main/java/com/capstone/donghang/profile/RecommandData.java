package com.capstone.donghang.profile;

public class RecommandData {

    int userId, getUserId2;
    String name, location, place, startDate, endData;

    RecommandData(String name, String location){

        this.name = name;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPlace() {
        return place;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndData() {
        return endData;
    }
}

