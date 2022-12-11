package com.example.pharmacy;
public class Custtbl {

    private int custid;
    private int orderid;
    private String custname,custphone,custemail;



    public Custtbl(int custid, String custname, String custphone, String custemail, int orderid) {
        this.custid = custid;
        this.orderid = orderid;
        this.custname = custname;
        this.custphone = custphone;
        this.custemail = custemail;
    }

    public int getCustid() {

        return custid;
    }

    public int getOrderid() {

        return orderid;
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



}

