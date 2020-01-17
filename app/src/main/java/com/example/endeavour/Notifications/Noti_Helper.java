package com.example.endeavour.Notifications;

public class Noti_Helper {

    String Title;
    String Desc;
    String notiid;
    String type;

    public Noti_Helper() {
    }

    public Noti_Helper(String title, String desc, String notiid, String type) {
        Title = title;
        Desc = desc;
        this.notiid = notiid;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
