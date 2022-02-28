/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CetakController implements Initializable {
    
    @FXML
    private Label Status;

    @FXML
    private TableColumn<menu, String> colHarga;

    @FXML
    private TableColumn<menu, String> colJumlah;

    @FXML
    private TableColumn<menu, String> colMenu;

    @FXML
    private TableColumn<menu, String> colSisa;

    @FXML
    private TableView<menu> tblView;

    private Connection conn;
    private Statement state;
    private ResultSet queryResult;
    
    private ObservableList<menu> getDataMenu;
    
    public void tampilData(){
      try {
          state = conn.createStatement();
          String sql = "SELECT * FROM tb_menu";
          queryResult = state.executeQuery(sql);
          this.getDataMenu = FXCollections.observableArrayList();
          
          while(queryResult.next()){
              this.getDataMenu.add(new menu(
              queryResult.getString("Menu"),
              queryResult.getString("Harga"),
              queryResult.getString("Jumlah"),
              queryResult.getString("Sisa")
              ));
         
          }
          
          this.colMenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
          this.colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
          this.colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
          this.colSisa.setCellValueFactory(new PropertyValueFactory<>("sisa"));
          
          this.tblView.setItems(this.getDataMenu);
      } catch (SQLException e){
          System.out.println("Gagal Push Data Menu" + e);
      }
    }
    
    @FXML
    private void btnKembali(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/user.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
       final String OFFICE_URL = "jdbc:mysql://localhost:3306/db_kasir";
       final String USERNAME = "root";
       final String PASSWORD = "";
      
      try {
          Class.forName(JDBC_DRIVER);
          conn = DriverManager.getConnection(OFFICE_URL, USERNAME, PASSWORD);
          tampilData();
      } catch (ClassNotFoundException e1){
          System.out.println("Driver MySQL JDBC gagal!!" + e1);
      } catch(SQLException e2){
          System.out.println("Error : " + e2);
      }
    }    
        
    
}
