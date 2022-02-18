/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.KasirRestoran;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label AlertText;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button Login;
    
    @FXML   
    public void userLogin(ActionEvent event) throws SQLException, IOException{
        if(UsernameField.getText().equals("admin") && PasswordField.getText().equals("admin123")){
            AlertText.setText("Login Berhasil");
            Parent root = FXMLLoader.load(getClass().getResource("/views/admin.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.setFullScreen(true);
            stage.show();
        } else if(UsernameField.getText().isEmpty() == false && PasswordField.getText().isEmpty() == false){
            AlertText.setText("Username/Password Tidak Terdaftar");
        } else {
            AlertText.setText("Masukkan Username/Password");
        }    
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
