package com.example.pharmacy;
import com.sun.javafx.scene.control.CustomColorDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.pharmacy.user;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.pharmacy.jdbcex;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class logcont implements Initializable{
    @FXML
    private Label invaliduser;
    @FXML
    private Label wrongpass;
    @FXML
    private Button loginbutton;
    @FXML
    private PasswordField password;

    @FXML
   private TextField userid;
    public static String userID;

    public void SendToHomepage(){
        userID =userid.getText();
        user.USERID=userID;

        String loginpass=password.getText();
        Connection logcon = jdbcex.getConnection();
        try {
            PreparedStatement st = logcon.prepareStatement("SELECT password from AHD.EMPLOYEE WHERE PERSON_ID=?");
            st.setString(1,userID);
            ResultSet res= st.executeQuery();
            if(!res.next()) {
                wrongpass.setVisible(false);

                invaliduser.setVisible(true);
            }
            String dbpass =res.getString("password");
            if(dbpass.equals(loginpass)){

                FXMLLoader loader1 = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
                logcont logscene = loader1.getController();
                Scene scene2 = new Scene(loader1.load(), 640, 400);
                Stage stage2 = new Stage();
                stage2.setScene(scene2);
                stage2.show();
                Stage stage = (Stage) loginbutton.getScene().getWindow();
                stage.close();


            }
            else{
                invaliduser.setVisible(false);

                wrongpass.setVisible(true);
            }
            logcon.close();

        }
        catch(Exception e){

        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginbutton.setOnMouseClicked(e->{
            SendToHomepage();

         });
    }
}


