package com.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;



public class jdbcex {

    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.4:1521:xe", "ahd", "123456");

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static ObservableList <Emptable> getDatausers()
    {
        Connection conn= getConnection();
        ObservableList <Emptable> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM AHD.EMPLOYEE");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Emptable(rs.getInt("PERSON_ID"), rs.getString("FNAME"), rs.getString("MOPILE_PHONE"),rs.getString("EMAIL"),rs.getInt("ISMANAGER"), rs.getInt( "SHIFT_TIME"),rs.getInt("WAGE"),rs.getString("PASSWORD") ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static ObservableList<Custtbl>  getcustusers()
    {
        Connection conn=getConnection();
        ObservableList<Custtbl> list= FXCollections.observableArrayList();
        try{
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM ahd.custemer");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Custtbl(rs.getInt("custemer_id"),rs.getString("FNAME"),rs.getString("MOPILE_PHONE"),rs.getString("EMAIL")));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static ObservableList<manuftbl>  getmanusers()
    {
        Connection conn=getConnection();
        ObservableList<manuftbl> list= FXCollections.observableArrayList();
        try{
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM ahd.manufacturer");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                list.add(new manuftbl(rs.getInt("manufacturer_id"),rs.getString("FNAME"),rs.getString("address"),rs.getString("EMAIL"),rs.getString("PHONE")));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}