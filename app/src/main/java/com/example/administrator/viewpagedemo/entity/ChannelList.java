package com.example.administrator.viewpagedemo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class ChannelList {
    String channelName;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    List<News> newsList;

    public ChannelList(String channelName, List<News> newsList) {
        this.channelName = channelName;
        this.newsList = newsList;
    }
}
