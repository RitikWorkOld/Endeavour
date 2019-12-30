package com.example.endeavour;

public class Events_main_model {

    String mimguri;
    String title;
    String descp;

    public Events_main_model() {
    }

    public Events_main_model(String mimguri, String title, String descp) {
        this.mimguri = mimguri;
        this.title = title;
        this.descp = descp;
    }

    public String getMimguri() {
        return mimguri;
    }

    public void setMimguri(String mimguri) {
        this.mimguri = mimguri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
