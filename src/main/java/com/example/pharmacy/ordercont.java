package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ordercont {

    @FXML
    private void SendToHomepage(ActionEvent event) throws IOException {

        FXMLLoader loader1 = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
        logcont logscene = loader1.getController();
        Scene scene2 = new Scene(loader1.load(), 640, 400);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();

    }

}
