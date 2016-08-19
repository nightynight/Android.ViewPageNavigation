package com.example.administrator.viewpagedemo.entity;

/**
 * Created by Administrator on 2016/8/16.
 */
public class News {
    String title;
    public News(String title) {
        this.title = title;
    }

    public News() {
    }
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
