package com.example.pharmacy;
import com.mysql.cj.xdevapi.UpdateType;
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
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Custcont implements Initializable {


    @FXML
    private TextField custidTextField;

    @FXML
    private TextField custnameTextField;

    @FXML
    private TextField custphoneTextField;


    @FXML
    private TextField custemailTextField;

    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;

    @FXML
    private TableView<Custtbl> table_cust;
    @FXML
    private TableColumn<Custtbl, String> custemail;
    @FXML
    private TableColumn<Custtbl, Integer> custid;
    @FXML
    private TableColumn<Custtbl, String> custname;
    @FXML
    private TableColumn<Custtbl, String> custphone;


    ObservableList<Custtbl> listm;
    int index = -1;
    Connection conn = null;
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

    public void getSelected() {
        index = table_cust.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        custidTextField.setText(custid.getCellData(index).toString());
        custnameTextField.setText(custname.getCellData(index).toString());
        custphoneTextField.setText(custphone.getCellData(index).toString());
        custemailTextField.setText(custemail.getCellData(index).toString());

    }


    public void Updatetable() {

        custid.setCellValueFactory(new PropertyValueFactory<Custtbl, Integer>("custid"));
        custname.setCellValueFactory(new PropertyValueFactory<Custtbl, String>("custname"));
        custphone.setCellValueFactory(new PropertyValueFactory<Custtbl, String>("custphone"));
        custphone.setCellValueFactory(new PropertyValueFactory<Custtbl, String>("custphone"));
        listm = jdbcex.getcustusers();
        table_cust.setItems(listm);


    }

    @FXML
    void deleteCust(ActionEvent event) {
        conn = jdbcex.getConnection();
        String id = custidTextField.getText();
        System.out.println(id + "");
        String sql1 = "DELETE FROM custemer WHERE custemer_id='" + id + "'";
        try {
            pst = conn.prepareStatement(sql1);
            //pst.setString(1,empidtxt.getText());

            pst.execute();
            table_cust.refresh();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Customer.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
            Stage stage = (Stage) delete.getScene().getWindow();
            stage.close();
            //updateEmployee();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void addcust(ActionEvent event){


        String custId = custidTextField.getText();
        conn = jdbcex.getConnection();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("SELECT custemer_id from ahd.custemer WHERE custemer_id=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            st.setString(1, custId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet res = null;
        try {
            res = st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (!res.next()) {

                String sql = "insert into ahd.custemer(Custemer_ID,FNAME,MOPILE_PHONE,EMAIL) values(?,?,?,?)";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, custidTextField.getText());
                    pst.setString(2, custnameTextField.getText());
                    pst.setString(3, custphoneTextField.getText());
                    pst.setString(4, custemailTextField.getText());

                    pst.execute();
                    //JOptionPane.showMessageDialog(null," customer has been added");
                    Updatetable();
                } catch (Exception e) {
                }
            } else {

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } {

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Updatetable();


        this.table_cust.setOnMouseClicked((e) -> {
            getSelected();
        });



}}

