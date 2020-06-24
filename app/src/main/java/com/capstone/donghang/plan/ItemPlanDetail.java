package com.capstone.donghang.plan;


public class ItemPlanDetail {
    private int type;
    private String date;
    private String title;
    private String address;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ItemPlanDetail(int type, String title, String address) {
        this.type = type;
        this.title = title;
        this.address = address;
    }

    public ItemPlanDetail(int type, String date) {
        this.type = type;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
