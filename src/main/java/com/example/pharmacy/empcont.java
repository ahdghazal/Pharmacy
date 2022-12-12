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
    @FXML
    public void Edit(ActionEvent event) {


        try {
            conn = jdbcex.getConnection();
            String v01 = empidtxt.getText();
            int v1= Integer.parseInt(v01);
            String v2 = empnametxt.getText();
            String v3 = empphonetxt.getText();
            String v4 = empemailtxt.getText();
            String v05 = wagetxt.getText();
            int v5= Integer.parseInt(v05);
            String v06 = shifttxt.getText();
            int v6= Integer.parseInt(v06);
            String v7 = passtxt.getText();
            String v8 = istxt.getText();
            try {
                String sql = "UPDATE EMPLOYEE SET FNAME='"+v2+"',MOPILE_PHONE='"+v3+"',EMAIL='"+v4+"',ISMANAGER ="+v8+",SHIFT_TIME="+v6+",WAGE="+v5+",PASSWORD='"+v7+"' WHERE PERSON_ID="+v1;


                st = conn.createStatement();
                st.executeUpdate(sql);
                emptable.refresh();
                updateEmployee();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Employee.fxml"));
                Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
                Stage stage2 = new Stage();
                stage2.setScene(scene2);
                stage2.show();
                Stage stage = (Stage) empupdate.getScene().getWindow();
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

    public void updateEmployee() {

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
    }

    @FXML
    void delete(ActionEvent event) {
        conn = jdbcex.getConnection();
        String id = empidtxt.getText();
        System.out.println(id+"");
        String sql1 = "DELETE FROM employee WHERE person_id='"  +id+ "'";
        try {
            pst=conn.prepareStatement(sql1);
            //pst.setString(1,empidtxt.getText());

            pst.execute();
            emptable.refresh();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Employee.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 600, 420);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
            Stage stage = (Stage) empdelete.getScene().getWindow();
            stage.close();
            //updateEmployee();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateEmployee();


        this.emptable.setOnMouseClicked((e) -> {
            getSelected();    });
            //this.empdelete.setOnMouseClicked((e) -> {

        //});



    }}




