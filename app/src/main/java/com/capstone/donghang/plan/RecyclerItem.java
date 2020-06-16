package com.capstone.donghang.plan;

public class RecyclerItem {
    private  String title;
    private String period;

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

    public RecyclerItem(String title, String period) {
        this.title = title;
        this.period = period;
    }

}
