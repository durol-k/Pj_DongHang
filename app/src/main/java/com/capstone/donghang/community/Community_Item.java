package com.capstone.donghang.community;

public class Community_Item {
    private String id;
    private String time;
    private String title;
    private String content;
    private int img;
    private int like, comment, view;

    public Community_Item(String id, String time, String title, String content, Integer img, int like, int comment, int view) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.content = content;
        if(img != null)
            this.img = img.intValue();
        this.like = like;
        this.comment = comment;
        this.view = view;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Community_Item{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img=" + img +
                ", like=" + like +
                ", comment=" + comment +
                ", view=" + view +
                '}';
    }
}
