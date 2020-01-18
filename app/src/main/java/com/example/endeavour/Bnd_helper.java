package com.example.endeavour;

public class Bnd_helper {

    public String refrelid;
    public String name;
    public String contactn;

    public Bnd_helper() {
    }


    public Bnd_helper(String refrelid, String name, String contactn) {
        this.refrelid = refrelid;
        this.name = name;
        this.contactn = contactn;
    }

    public String getRefrelid() {
        return refrelid;
    }

    public void setRefrelid(String refrelid) {
        this.refrelid = refrelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactn() {
        return contactn;
    }

    public void setContactn(String contactn) {
        this.contactn = contactn;
    }
}
