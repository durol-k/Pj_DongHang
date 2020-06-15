package com.capstone.donghang.community;

public class Fragment_Community_MainListItem {
    private String id;
    private String time;
    private String title;
    private String content;
    private int img;
    private int like, comment, viewCount;
    private int icon;
    public Fragment_Community_MainListItem(String id, String time, String title, String content, Integer img, int like, int comment, int viewCount, int icon) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.content = content;
        if(img != null)
            this.img = img.intValue();
        this.like = like;
        this.comment = comment;
        this.viewCount = viewCount;
        this.icon = icon;
    }

    public int getImg() {
        return img;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }


    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }


    public int getLike() {
        return like;
    }


    public int getComment() {
        return comment;
    }


    public int getView() {
        return viewCount;
    }

    public int getIcon() {
        return icon;
    }
}
