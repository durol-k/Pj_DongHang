package com.capstone.donghang.profile;

public class PostData {

    int post_num, user_num, post_type1;
    String post_title, post_content, url;
    String write_date;
    String post_type;

    PostData(String title, String content){
        post_title = title;
        post_content = content;
    }

    PostData(String title, String content, String url){
        post_title = title;
        post_content = content;
        this.url = url;
    }

    PostData(String post_title, String post_type, String write_date, String post_content){
        this.post_title = post_title;
        this.post_type = post_type;
        this.write_date = write_date;
        this.post_content = post_content;
    }

    public int getPost_num() {
        return post_num;
    }

    public int getPost_type() {
        return post_type1;
    }

    public int getUser_num() {
        return user_num;
    }

    public String getPost_content() {
        return post_content;
    }

    public String getPost_title() {
        return post_title;
    }

}
