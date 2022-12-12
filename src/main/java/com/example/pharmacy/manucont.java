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
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class manucont  implements Initializable{

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
    Connection conn =null;

    ResultSet rs = null;
    Statement st = null;
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

    @FXML
    public void Edit(ActionEvent event) {


        try {
            conn = jdbcex.getConnection();
            String v01 = manufidTextField.getText();
            int v1= Integer.parseInt(v01);
            String v2 = manufnameTextField.getText();
            String v3 = manufaddressTextField.getText();
            String v4 = manufemailTextField.getText();
            String v5 = manufphoneTextField.getText();
            try {
                String sql = "UPDATE manufacturer SET manufacturer_ID="+v1+",FNAME='"+v2+"',address='"+v3+"',EMAIL='"+v4+"',PHONE='"+v5+"' WHERE manufacturer_ID="+v1;


                st = conn.createStatement();
                st.executeUpdate(sql);
                table_manuf.refresh();
                Updatemanu();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("manufacture.fxml"));
                Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
                Stage stage2 = new Stage();
                stage2.setScene(scene2);
                stage2.show();
                Stage stage = (Stage) update.getScene().getWindow();
                stage.close();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Updatemanu() {

        col_manufid.setCellValueFactory(new PropertyValueFactory<manuftbl, Integer>("manufid"));
        col_manufname.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufname"));
        col_manufphone.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufphone"));
        col_manufemail.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufemail"));
        col_manufaddress.setCellValueFactory(new PropertyValueFactory<manuftbl, String>("manufaddress"));
        listm = jdbcex.getmanusers();
        table_manuf.setItems(listm);

    }

    @FXML
    void deleteManufacturer(ActionEvent event) {
        conn = jdbcex.getConnection();
        String id = manufidTextField.getText();
        System.out.println(id + "");
        String sql1 = "DELETE FROM ahd.manufacturer WHERE manufacturer_id=" + id ;
        try {
            st = conn.createStatement();
            st.executeUpdate(sql1);

            table_manuf.refresh();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manufacture.fxml"));
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
    void addManufacturer(ActionEvent event) throws SQLException {
        String manuId = manufidTextField.getText();
        conn = jdbcex.getConnection();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("SELECT manufacturer_id from ahd.manufacturer WHERE manufacturer_id=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            st.setString(1, manuId);
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
                    Updatemanu();
                } catch (Exception e) {
                    e.printStackTrace();
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
        Updatemanu();


        this.table_manuf.setOnMouseClicked((e) -> {
            getSelected();
        });



    }
}