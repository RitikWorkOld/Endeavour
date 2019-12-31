package com.example.endeavour;

public class Events_main_model {

    String mimguri;
    String title;
    String descp;
    String simguri;
    String register_uri;
    String desc1;
    String desc2;

    public Events_main_model() {
    }

    public Events_main_model(String mimguri, String title, String descp, String simguri, String register_uri, String desc1, String desc2) {
        this.mimguri = mimguri;
        this.title = title;
        this.descp = descp;
        this.simguri = simguri;
        this.register_uri = register_uri;
        this.desc1 = desc1;
        this.desc2 = desc2;
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

    public String getSimguri() {
        return simguri;
    }

    public void setSimguri(String simguri) {
        this.simguri = simguri;
    }

    public String getRegister_uri() {
        return register_uri;
    }

    public void setRegister_uri(String register_uri) {
        this.register_uri = register_uri;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }
}
