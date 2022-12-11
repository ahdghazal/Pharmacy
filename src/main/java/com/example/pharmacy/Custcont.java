package com.example.pharmacy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Custcont implements Initializable {

    @FXML
    private TableView<Custtbl> custtbl;
    @FXML
    private TableColumn<Custtbl, String> custemail;
    @FXML
    private TableColumn<Custtbl, Integer> custid;
    @FXML
    private TableColumn<Custtbl, String> custname;
    @FXML
    private TableColumn<Custtbl, String> custphone;
    @FXML
    private TableColumn<Custtbl, Integer> orderid;

    ObservableList<Custtbl> listcust = FXCollections.observableArrayList(
            new Custtbl(120, "manar", "0599876543", "manar@gmail.com", 12),
            new Custtbl(130, "lana", "0598666420", "lana@gmail.com", 13)
    );


    @FXML
    private void SendToHomepage(ActionEvent event) throws IOException {

        FXMLLoader loader1 = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
        logcont logscene = loader1.getController();
        Scene scene2 = new Scene(loader1.load(), 640, 400);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custid.setCellValueFactory(new PropertyValueFactory<Custtbl, Integer>("custid"));
        custname.setCellValueFactory(new PropertyValueFactory<Custtbl, String>("custname"));
        custphone.setCellValueFactory(new PropertyValueFactory<Custtbl, String>("custphone"));
        custphone.setCellValueFactory(new PropertyValueFactory<Custtbl, String>("custphone"));
        orderid.setCellValueFactory(new PropertyValueFactory<Custtbl, Integer>("orderid"));
        custtbl.setItems(listcust);
    }
}
