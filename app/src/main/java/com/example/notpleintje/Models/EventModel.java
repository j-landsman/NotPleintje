package com.example.notpleintje.Models;

public class EventModel {
    private String day, month, title, place, time, count, creator, url, desc;

    public EventModel(String day, String month, String title, String place, String count, String time, String creator, String url, String desc) {
        this.day = day;
        this.month = month;
        this.title = title;
        this.place = place;
        this.count = count;
        this.time = time;
        this.creator = creator;
        this.url = url;
        this.desc = desc;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getCount() {
        return count;
    }

    public String getTime() {
        return time;
    }

    public String getCreator() {
        return creator;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }
}
