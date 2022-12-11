package com.example.pharmacy;

public class Emptable {

    private int empid;
    private int ismanager;
    private int wage;
    private int shifthours;
    private String empname,empphone,empemail,password;

    public Emptable(int empid,String empname,String empphone,String empemail, int ismanager,int wage,int shifthours, String password){
        this.empid=empid;
        this.empname=empname;
        this.empphone=empphone;
        this.empemail=empemail;
        this.wage=wage;
        this.shifthours=shifthours;
        this.password=password;
        this.ismanager=ismanager;
    }
    public int getEmpid() {
        return empid;
    }

    public int getIsmanager() {
        return ismanager;
    }

    public int getWage() {
        return wage;
    }

    public int getShifthours() {
        return shifthours;
    }

    public String getEmpname() {
        return empname;
    }

    public String getEmpphone() {
        return empphone;
    }

    public String getEmpemail() {
        return empemail;
    }

    public String getPassword() {
        return password;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public void setIsmanager(int ismanager) {
        this.ismanager = ismanager;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setShifthours(int shifthours) {
        this.shifthours = shifthours;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public void setEmpphone(String empphone) {
        this.empphone = empphone;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
