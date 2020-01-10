package com.example.endeavour.services;

public class Noti_Helper {

    String Title;
    String Desc;
    String notiid;

    public Noti_Helper() {
    }

    public Noti_Helper(String title, String desc, String notiid) {
        this.Title = title;
        this.Desc = desc;
        this.notiid = notiid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getNotiid() {
        return notiid;
    }

    public void setNotiid(String notiid) {
        this.notiid = notiid;
    }
}
