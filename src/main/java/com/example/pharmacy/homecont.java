package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.example.pharmacy.user;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class homecont implements Initializable {
    @FXML
    private Button cusbtn;
    @FXML
    private Button empbtn;
    @FXML
    private Button ordbtn;
    @FXML
    private Button outbtn;
    @FXML
    private Button prdbtn;
    @FXML
    private Button sigbtn;
    @FXML
    private Button supbtn;
    @FXML
    private Label passid;
    @FXML
    private Label wlcst;

    @FXML
    private Label custlbl;

    @FXML
    private Label emplbl;

    @FXML
    private Label explbl;

    @FXML
    private Label manulbl;

    @FXML
    private Label ordlbl;

    @FXML
    private Label prodlbl;

    @FXML
    private void SendToCustomers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Customer.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
        Stage stage = (Stage) cusbtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void SendToEmployee() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Employee.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
        Stage stage = (Stage) empbtn.getScene().getWindow();
        stage.close();

        }

    @FXML
    private void SendToHomepage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginpage.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 640, 458);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
        Stage stage = (Stage) outbtn.getScene().getWindow();
        stage.close();


    }

    @FXML
    private void SendToOrders(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Order.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
        Stage stage = (Stage) ordbtn.getScene().getWindow();
        stage.close();


    }

    @FXML
    private void SendToProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("product.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
        Stage stage = (Stage) prdbtn.getScene().getWindow();
        stage.close();

    }
    @FXML
    private void SendToSupplier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manufacture.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
        Stage stage = (Stage) supbtn.getScene().getWindow();
        stage.close();


    }
    @FXML
    private void SendToSign(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("signup.fxml"));
        logcont logscene = loader.getController();
        Scene scene1 = new Scene(loader.load(), 640, 458);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
        Stage stage = (Stage) sigbtn.getScene().getWindow();
        stage.close();


    }

       public static String pass;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id=user.USERID;
        Connection homecon = jdbcex.getConnection();
        try {

            Statement stmt0 = homecon.createStatement();
            String query0 = "select ismanager from AHD.EMPLOYEE where person_id="+id;
            ResultSet rs0 = stmt0.executeQuery(query0);
            rs0.next();
            int mg = rs0.getInt(1);

            if(mg==0){
                sigbtn.setVisible(false);
            }
            else
                sigbtn.setVisible(true);
            Statement stmt00 = homecon.createStatement();
            String query00 = "select fname from AHD.EMPLOYEE where person_id="+id;
            ResultSet rs00 = stmt00.executeQuery(query00);
            rs00.next();
            String wlc = rs00.getString(1);
            wlcst.setText("Hi "+wlc+"!, Have a nice shift!♡");



            Statement stmt1 = homecon.createStatement();
            String query1 = "select count(*) from AHD.EMPLOYEE";
            ResultSet rs1 = stmt1.executeQuery(query1);
            rs1.next();
            int EMLB = rs1.getInt(1);
            String em = Integer.toString(EMLB);
            emplbl.setText(em);

            Statement stmt2 = homecon.createStatement();
            String query2 = "select count(*) from AHD.PRODUCT";
            ResultSet rs2 = stmt2.executeQuery(query2);
            rs2.next();
            int PRDLB = rs2.getInt(1);
            String pr = Integer.toString(PRDLB);
            prodlbl.setText(pr);

            Statement stmt3 = homecon.createStatement();
            String query3 = "select count(*) from AHD.CUSTEMER WHERE EMPLOYEE_ID ="+id;
            ResultSet rs3 = stmt3.executeQuery(query3);
            rs3.next();
            int CSLB = rs3.getInt(1);
            String cs = Integer.toString(CSLB);
            custlbl.setText(cs);



            Statement stmt4 = homecon.createStatement();
            String query4 = "select count(*) from AHD.ORDERS WHERE EMPLOYEE_ID ="+id;
            ResultSet rs4 = stmt4.executeQuery(query4);
            rs4.next();
            int ORDLB = rs4.getInt(1);
            String ord = Integer.toString(ORDLB);
            ordlbl.setText(ord);

            Statement stmt5 = homecon.createStatement();
            String query5 = "select count(*) from AHD.MANUFACTURER";
            ResultSet rs5 = stmt5.executeQuery(query5);
            rs5.next();
            int MANLB = rs5.getInt(1);
            String mn = Integer.toString(MANLB);
            manulbl.setText(mn);

            Statement stmt6 = homecon.createStatement();
            String query6 = "select count(*) from AHD.PRODUCT WHERE EXPIRY_DATE < '01/JAN/2024'";
            ResultSet rs6 = stmt6.executeQuery(query6);
            rs6.next();
            int EXPLB = rs6.getInt(1);
            String ex = Integer.toString(EXPLB);
            explbl.setText(ex);

        } catch (Exception ex) {
             ex.printStackTrace();
        }









    }
}
