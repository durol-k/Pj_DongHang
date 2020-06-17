package com.capstone.donghang.plan;

import java.util.List;

public class ItemPlanDetail {
    private  String title;
    private String period;
    List item;

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

    public ItemPlanDetail(String title, String period) {
        this.title = title;
        this.period = period;
    }

}
