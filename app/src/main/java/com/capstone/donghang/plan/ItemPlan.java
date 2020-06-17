package com.capstone.donghang.plan;

import java.util.ArrayList;

public class ItemPlan {
    private String title;
    private String period;
    private ArrayList<String> tags;

    public ItemPlan(String title, String period, ArrayList<String> tags) {
        this.title = title;
        this.period = period;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

}
