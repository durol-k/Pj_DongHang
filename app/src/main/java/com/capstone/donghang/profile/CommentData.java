package com.capstone.donghang.profile;

public class CommentData {

    int post_num, user_num;
    String post_title, comment_content;
    String write_date;

    CommentData(String title, String content){
        post_title = title;
        comment_content = content;
    }


    CommentData(String title, String write_date, String content){
        post_title = title;
        this.write_date = write_date;
        comment_content = content;
    }

    public int getPost_num() {
        return post_num;
    }

    public String getWrite_date() {
        return write_date;
    }

    public int getUser_num() {
        return user_num;
    }

    public String getComment_content() { return comment_content;    }

    public String getPost_title() {
        return post_title;
    }

}
