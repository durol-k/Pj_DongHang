package com.capstone.donghang.community;

/**
 댓글에 대한 정보 담는 클래스
 */
public class Fragment_Community_Comment_Item {
    private String id, time, content;
    private int icon;

    public Fragment_Community_Comment_Item(String id, String time, String content, int icon) {
        this.id = id;
        this.time = time;
        this.content = content;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getIcon() {
        return icon;
    }
}
