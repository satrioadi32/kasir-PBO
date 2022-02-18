/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import app.KasirRestoran;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UserController implements Initializable {
    
    @FXML
    private Label Status;
    
    @FXML
    private Label miniStatus;
    
    @FXML
    private Button btnDesserts;

    @FXML
    private Button btnMakanan;

    @FXML
    private Button btnMinuman;
    

    @FXML
    public void btnLoginAdmin(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
       Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setScene(scene);
       //stage.setFullScreen(true);
       stage.show();
    }
    
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == btnMakanan){
            Status.setText("Pilih Makananmu");
            miniStatus.setText("/beranda/menu makanan");
        } else if(event.getSource() == btnMinuman) {
            Status.setText("Pilih Minumanmu");
            miniStatus.setText("/beranda/menu minuman");
        } else if(event.getSource() == btnDesserts) {
            Status.setText("Pilih Dessertmu");
            miniStatus.setText("/beranda/menu dessert");
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
