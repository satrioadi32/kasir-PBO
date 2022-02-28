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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdminController implements Initializable {  

    @FXML
    private TextField HargaField;

    @FXML
    private TextField JumlahField;

    @FXML
    private TextField MenuField;

    @FXML
    private TextField SisaField;

    @FXML
    private Label Status;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDesserts;

    @FXML
    private Button btnMakanan;

    @FXML
    private Button btnMinuman;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<menu, String> colHarga;

    @FXML
    private TableColumn<menu, String> colJumlah;

    @FXML
    private TableColumn<menu, String> colMenu;

    @FXML
    private TableColumn<menu, String> colSisa;

    @FXML
    private Label miniStatus;

    @FXML
    private TableView<menu> tblView;    
    
    private Connection conn;
    private Statement state;
    private ResultSet queryResult;
    
    private ObservableList<menu> getDataMenu;
    
    String getData;
    
    @FXML
    public void btnLogout(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("/views/user.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setFullScreen(true);
    stage.show();
    }
    
    @FXML
    private void handleClicks(ActionEvent event) throws SQLException{
        if(event.getSource() == btnMakanan){
            Status.setText("Data Makanan");
            miniStatus.setText("/beranda/menu makanan");
        } else if(event.getSource() == btnMinuman){
            Status.setText("Data Minuman");
            miniStatus.setText("/beranda/menu minuman");
        } else if(event.getSource() == btnDesserts){
            Status.setText("Data Dessert");
            miniStatus.setText("/beranda/menu dessert");
        }
    }
    
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
    private void pilihData(MouseEvent event) throws SQLException{
        try {
            menu selectedItem = tblView.getSelectionModel().getSelectedItem();
            String sql = "SELECT * FROM tb_menu";
            queryResult = state.executeQuery(sql);

            this.MenuField.setText(selectedItem.getMenu());
            this.HargaField.setText(selectedItem.getHarga());
            this.JumlahField.setText(selectedItem.getJumlah());
            this.SisaField.setText(selectedItem.getSisa());
            
            this.getData = selectedItem.getMenu();
            
            
        } catch (SQLException e){
            System.out.println(e);
        }
        
    }
    
    @FXML
    private void tambahData(ActionEvent event) throws SQLException{
        String menu = MenuField.getText().trim();
        String harga = HargaField.getText().trim();
        String jumlah = JumlahField.getText().trim();
        String sisa = SisaField.getText().trim();
        
        if (menu.equals("") || harga.equals("") || jumlah.equals("") || sisa.equals("")){
            AlertMessage(1, "Data Harus Diisi");
        } else {
            try {
                String pilihData = "SELECT * FROM tb_menu WHERE menu= '" + menu + "' ";
                queryResult = state.executeQuery(pilihData);
                int record = 0;
                
                while (queryResult.next()){
                    record++;
                }
                if(record > 0){
                    AlertMessage(1,"Menu Sudah Tersedia");
                } else {
                    String insertData = "INSERT INTO tb_menu VALUES('"+ menu +"', '"+ harga +"', '"+ jumlah +"', '"+ sisa +"')";
                    state = conn.createStatement();
                    int result = state.executeUpdate(insertData);
                    
                    if(result > 0){
                        AlertMessage(0, "Tambah Data Menu Behasil");
                        MenuField.setText("");
                        HargaField.setText("");
                        JumlahField.setText("");
                        SisaField.setText("");
                        
                        tampilData();
                    }
                }
                
            } catch (SQLException e){
                System.out.println("Gagal Simpan Data " + e);
            }
        }
    }
    
    @FXML
    private void updateData(ActionEvent event) throws SQLException{
        String menu = MenuField.getText().trim();
        String harga = HargaField.getText().trim();
        String jumlah = JumlahField.getText().trim();
        String sisa = SisaField.getText().trim();
        
        try {
            menu selectedItem = tblView.getSelectionModel().getSelectedItem();
            String pilihData = "UPDATE tb_menu SET harga ='" + harga + "', jumlah ='" + jumlah + "', sisa ='" + sisa + "' WHERE menu = '" + this.getData + "'";
            state.executeUpdate(pilihData);
            
            AlertMessage(0, "Data berhasil Di Update");
            
            MenuField.setText("");
            HargaField.setText("");
            JumlahField.setText("");
            SisaField.setText("");
            
            tblView.getItems().clear();
            tampilData();
        } catch (SQLException e){
            System.out.println(e);
        }
    }
    
    @FXML
    private void deleteData(ActionEvent event) throws SQLException{
       try {
            menu selectedItem = tblView.getSelectionModel().getSelectedItem();
            String sql = "DELETE  FROM tb_menu WHERE menu= '" + this.getData + "'";
            state.executeUpdate(sql);
            
            AlertMessage(0, "Delete Data Berhasil");
            
            MenuField.setText("");
            HargaField.setText("");
            JumlahField.setText("");
            SisaField.setText("");
            
            tblView.getItems().clear();
            this.tampilData();
        } catch (SQLException e){
            System.out.println(e);
        }
    }
    
    @FXML
    private void refreshData(ActionEvent event) throws SQLException{
        try {
            this.getDataMenu.clear();
            String pilihData = "SELECT * FROM tb_menu";
            state = conn.createStatement();
            queryResult = state.executeQuery(pilihData);
            
            if(queryResult.next()) {
                this.getDataMenu.add(new menu(
                        queryResult.getString("Menu"),
                        queryResult.getString("Harga"),
                        queryResult.getString("Jumlah"),
                        queryResult.getString("Sisa")
                ));
                AlertMessage(0, "Data Berhasil Di Refresh");    
            } else {
                AlertMessage(1, "Data Tidak/Belum Tersedia");
            }

            this.tblView.setItems(getDataMenu);
        } catch (SQLException e){
            System.out.println("Gagal Refresh Data " + e);
        }
    }
    private void AlertMessage(int type, String message){
        Alert alert = null;
        if (type == 0){
            alert = new Alert(AlertType.INFORMATION);
        } else if (type == 1){
            alert = new Alert(AlertType.WARNING);
        }
        alert.setTitle("Info");
        alert.setContentText(message);
        alert.showAndWait();
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
