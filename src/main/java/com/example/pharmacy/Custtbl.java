package com.example.pharmacy;
public class Custtbl {

    private int custid;

    private String custname;


    private String custphone;
    private String custemail;



    public Custtbl(int custid, String custname, String custphone, String custemail) {
        this.custid = custid;

        this.custname = custname;
        this.custphone = custphone;
        this.custemail = custemail;
    }

    public int getCustid() {

        return custid;
    }




    public String getCustname() {

        return custname;
    }

    public String getCustphone() {

        return custphone;
    }

    public String getCustemail() {

        return custemail;
    }
    public void setCustid(int custid) {
        this.custid = custid;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public void setCustphone(String custphone) {
        this.custphone = custphone;
    }

    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }



}

