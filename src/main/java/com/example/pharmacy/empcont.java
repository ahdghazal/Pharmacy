package com.example.pharmacy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class empcont implements Initializable {

    @FXML
    private Button empdelete;
    @FXML
    private Button empupdate;
    @FXML
    private TextField empidtxt;
    @FXML
    private TextField empnametxt;
    @FXML
    private TextField empphonetxt;
    @FXML
    private TextField empemailtxt;
    @FXML
    private TextField wagetxt;
    @FXML
    private TextField shifttxt;
    @FXML
    private TextField passtxt;
    @FXML
    private TextField istxt;

    @FXML
    private TableView<Emptable> emptable;

    @FXML
    private TableColumn<Emptable, String> empemail;

    @FXML
    private TableColumn<Emptable, Integer> empid;

    @FXML
    private TableColumn<Emptable, String> empname;

    @FXML
    private TableColumn<Emptable, String> empphone;

    @FXML
    private TableColumn<Emptable, Integer> ismanager;

    @FXML
    private TableColumn<Emptable, String> password;

    @FXML
    private TableColumn<Emptable, Integer> shifthours;

    @FXML
    private TableColumn<Emptable, Integer> wage;



    ObservableList<Emptable> listm;
    int index = -1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
        private void SendToHomepage(ActionEvent event) throws IOException {

            FXMLLoader loader1 = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
            logcont logscene = loader1.getController();
            Scene scene2 = new Scene(loader1.load(), 640, 400);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();


        }


        ///////method to select user/////
    public void getSelected(){
        index = emptable.getSelectionModel().getFocusedIndex();
        if(index<=-1){return;}
        empidtxt.setText(empid.getCellData(index).toString());
        empnametxt.setText(empname.getCellData(index).toString());

        empphonetxt.setText(empphone.getCellData(index).toString());

        empemailtxt.setText(empemail.getCellData(index).toString());

        wagetxt.setText(wage.getCellData(index).toString());

        shifttxt.setText(shifthours.getCellData(index).toString());

        passtxt.setText(password.getCellData(index).toString());

        istxt.setText(ismanager.getCellData(index).toString());


    }
    public void updateEmployee(){
        try{
            conn= jdbcex.getConnection();
            String v1 = empidtxt.getText();
            String v2 = empnametxt.getText();
            String v3 = empphonetxt.getText();
            String v4 = empemailtxt.getText();
            String v5 = wagetxt.getText();
            String v6 = shifttxt.getText();
            String v7 = passtxt.getText();
            String v8 = ismanager.getText();

            String sql = "UPDATE EMPLOYEE SET PERSON_ID="+v1+",FNAME = '"+v2+"',MOPILE_PHONE='"+v3+"', EMAIL='"+v4+"',ISMANAGER ="+v8+",SHIFT_TIME="+v6+",WAGE="
+v5+",PASSWORD='"+v7+"'";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Updated!");
                          }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empid.setCellValueFactory(new PropertyValueFactory<Emptable,Integer>("empid"));
        wage.setCellValueFactory(new PropertyValueFactory<Emptable,Integer>("wage"));
        shifthours.setCellValueFactory(new PropertyValueFactory<Emptable,Integer>("shifthours"));
        ismanager.setCellValueFactory(new PropertyValueFactory<Emptable,Integer>("ismanager"));
        empname.setCellValueFactory(new PropertyValueFactory<Emptable,String>("empname"));
        empemail.setCellValueFactory(new PropertyValueFactory<Emptable,String>("empemail"));
        empphone.setCellValueFactory(new PropertyValueFactory<Emptable,String>("empphone"));
        password.setCellValueFactory(new PropertyValueFactory<Emptable,String>("password"));
        listm=jdbcex.getDatausers();
        emptable.setItems(listm);

        this.emptable.setOnMouseClicked((e) -> {
            getSelected();
        });
        this.empupdate.setOnMouseClicked((e) -> {
            updateEmployee();
        });
    }}



