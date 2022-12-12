package com.example.pharmacy;

public class manuftbl {
    private int manufid;
    private String manufname;
    private String manufaddress;
    private String manufemail;
    private String manufphone;

    public manuftbl(int manufid, String manufname, String manufaddress, String manufemail, String manufphone) {
        this.manufid = manufid;
        this.manufname = manufname;
        this.manufaddress = manufaddress;
        this.manufemail = manufemail;
        this.manufphone = manufphone;
    }

    public int getManufid() {
        return manufid;
    }

    public String getManufname() {
        return manufname;
    }

    public String getManufaddress() {
        return manufaddress;
    }

    public String getManufemail() {
        return manufemail;
    }

    public String getManufphone() {
        return manufphone;
    }

    public void setManufid(int manufid) {
        this.manufid = manufid;
    }

    public void setManufname(String manufname) {
        this.manufname = manufname;
    }

    public void setManufaddress(String manufaddress) {
        this.manufaddress = manufaddress;
    }

    public void setManufemail(String manufemail) {
        this.manufemail = manufemail;
    }

    public void setManufphone(String manufphone) {
        this.manufphone = manufphone;
    }
}