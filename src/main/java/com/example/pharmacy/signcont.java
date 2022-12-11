package com.example.pharmacy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class signcont implements Initializable {
        @FXML
        private TextField empemailtb;

        @FXML
        private TextField empidtb;

        @FXML
        private TextField empnametb;

        @FXML
        private TextField empphonetb;

        @FXML
        private CheckBox ismanagerchb;

        @FXML
        private PasswordField passconftb;

        @FXML
        private PasswordField passwordtb;

        @FXML
        private TextField shifttimetb;

        @FXML
        private Button signupbtn;

        @FXML
        private TextField wagetb;

        @FXML
        private Label matchwarn;


        public void SendToLogin() {

            String EMPIDTB = empidtb.getText();
            String EMPNAMETB= empnametb.getText();
            String EMPPHONETB = empphonetb.getText();
            String EMPEMAILTB = empemailtb.getText();
            String PASSWORD =passwordtb.getText();
            String PASSCONF = passconftb.getText();
            if (!(PASSWORD.equals(PASSCONF)))
            {
                matchwarn.setVisible(true);
                return;
            }
            else {
                matchwarn.setVisible(false);
            }
            String WAGETB = wagetb.getText();
            String SHIFTTIMETB = shifttimetb.getText();
            int ism;
            if(ismanagerchb.isSelected()){
                ism=1;
            }
            else
                ism=0;

            Connection signcon = jdbcex.getConnection();
            try {


                Statement st = signcon.createStatement();

                String insertst = "INSERT INTO EMPLOYEE VALUES (" + EMPIDTB + ",'" + EMPNAMETB + "','" + EMPPHONETB + "','" + EMPEMAILTB + "'," + ism + "," + SHIFTTIMETB + "," + WAGETB + ",'" + PASSWORD + "')";
                st.executeUpdate(insertst);

                signcon.close();

                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("loginpage.fxml"));
                logcont logscene = loader.getController();
                Scene scene0 = new Scene(loader.load(), 640, 458);
                Stage stage0 = new Stage();
                stage0.setScene(scene0);
                stage0.show();
                Stage stage = (Stage) signupbtn.getScene().getWindow();
                stage.close();

            }
            catch (SQLIntegrityConstraintViolationException s){
                JOptionPane.showInputDialog("USERID ALREADY EXISTS!");
            }
            catch(Exception e){
                e.printStackTrace();
            }


        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signupbtn.setOnMouseClicked(e->{
            SendToLogin();


        });
    }
}

