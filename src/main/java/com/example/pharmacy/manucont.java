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


public class manucont {

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private TextField manufidTextField;

    @FXML
    private TextField manufnameTextField;

    @FXML
    private TextField manufphoneTextField;

    @FXML
    private TextField manufaddressTextField;

    @FXML
    private TextField manufemailTextField;

    @FXML
    private TableView<manuftbl>  table_manuf;

    @FXML
    private TableColumn<manuftbl, Integer> col_manufid;

    @FXML
    private TableColumn<manuftbl, String> col_manufname;

    @FXML
    private TableColumn<manuftbl, String> col_manufphone;

    @FXML
    private TableColumn<manuftbl,String> col_manufemail;

    @FXML
    private TableColumn<manuftbl, String> col_manufaddress;

    @FXML
    private Button delete;


    ObservableList<manuftbl> listm;
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
        index = table_manuf.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        manufidTextField.setText(col_manufid.getCellData(index).toString());
        manufnameTextField.setText(col_manufname.getCellData(index).toString());
        manufphoneTextField.setText(col_manufphone.getCellData(index).toString());
        manufaddressTextField.setText(col_manufaddress.getCellData(index).toString());
        manufemailTextField.setText(col_manufemail.getCellData(index).toString());

    }
    public void Updatemanufacture() {

        col_manufid.setCellValueFactory(new PropertyValueFactory<manuftbl, Integer>("manufid"));
        col_manufname.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufname"));
        col_manufaddress.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufaddress"));
        col_manufemail.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufemail"));
        col_manufphone.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufphone"));
        listm = jdbcex.getmanusers();
        table_manuf.setItems(listm);

    }

    @FXML
    void deleteManufacturer(ActionEvent event) {
        conn = jdbcex.getConnection();
        String id = manufidTextField.getText();
        System.out.println(id + "");
        String sql1 = "DELETE FROM manufacturer WHERE manufacturer_id='" + id + "'";
        try {
            pst = conn.prepareStatement(sql1);

            pst.execute();
            table_manuf.refresh();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manufacturer.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
            Stage stage = (Stage) delete.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    void addManufacturer(ActionEvent event) throws SQLException {
        String manuID = manufidTextField.getText();
        conn = jdbcex.getConnection();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("SELECT manufacturer_id from ahd.manufacturer WHERE manufacturer_id=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            st.setString(1, manuID);
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

                String sql = "insert into ahd.manufacturer(manufacturer_ID,FNAME,address,email,PHONE) values(?,?,?,?.?)";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, manufidTextField.getText());
                    pst.setString(2, manufnameTextField.getText());
                    pst.setString(3, manufaddressTextField.getText());
                    pst.setString(4, manufemailTextField.getText());
                    pst.setString(5, manufphoneTextField.getText());

                    pst.execute();
                    Updatemanufacture();
                } catch (Exception e) {
                }
            } else {

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } {

        }
    }


    @FXML
    void updateManufacturer(ActionEvent event) {

    }

}
