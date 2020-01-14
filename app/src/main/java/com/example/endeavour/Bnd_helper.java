package com.example.endeavour;

public class Bnd_helper {

    public String refrelid;
    public String name;
    public String contactN;

    public Bnd_helper() {
    }

    public Bnd_helper(String refrelid, String name, String contactN) {
        this.refrelid = refrelid;
        this.name = name;
        this.contactN = contactN;
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

    public String getContactN() {
        return contactN;
    }

    public void setContactN(String contactN) {
        this.contactN = contactN;
    }
}
