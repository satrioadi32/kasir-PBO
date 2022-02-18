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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdminController implements Initializable {   
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
    private Button btnUpdate;
    
    @FXML
    private Button btnDelete;
    
    @FXML
    private TextField HargaField;

    @FXML
    private TextField JumlahField;

    @FXML
    private TextField MenuField;

    @FXML
    private TextField SisaField;

    
    @FXML
    private TableView<menu> tblView;
    
    @FXML
    private TableColumn<menu, Integer> colHarga;

    @FXML
    private TableColumn<menu, Integer> colJumlah;

    @FXML
    private TableColumn<menu, String> colMenu;

    @FXML
    private TableColumn<menu, Integer> colSisa;
    
    @FXML
    private TableColumn<menu, Button> colAction;
    
    @FXML
    public void btnLogout(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("/views/user.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    //stage.setFullScreen(true);
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
    
    private Connection conn;
    private Statement state;
    private ResultSet queryResult;
    
    private ObservableList<menu> getMenu;
    
    String data_menu;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String OFFICE_URL = "jdbc:mysql://localhost/db_kasir";
        final String USERNAME = "root";
        final String PASSWORD = "";
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(OFFICE_URL, USERNAME, PASSWORD);
            
            connect();
        } catch (ClassNotFoundException e1) {
           System.out.println("Driver MySQL JDBC failed" + e1);
        } catch (SQLException e2) {
            System.out.println("Error :" + e2);
        } 
    }
     
    private void connect() throws SQLException{
        try {
            state = conn.createStatement();
            String sql = "SELECT * FROM tb_menu";
            ResultSet queryResult = state.executeQuery(sql);
            
            this.getMenu = FXCollections.observableArrayList();
            
            while(queryResult.next()){
                this.getMenu.add(new menu(
                        queryResult.getString("menu"),
                        queryResult.getInt("harga"),
                        queryResult.getInt("jumlah"),
                        queryResult.getInt("sisa")
                ));
            }
            
            this.colMenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
            this.colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
            this.colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
            this.colSisa.setCellValueFactory(new PropertyValueFactory<>("sisa"));
            
            this.tblView.setItems(this.getMenu);
        } catch (SQLException e){
            System.out.println("Gagal upload Data Menu !!" + e);
        }
            
    }
    
    @FXML
    private void tambahData() throws SQLException{
        String menu = MenuField.getText().trim();
        String harga = HargaField.getText().trim();
        String jumlah = JumlahField.getText().trim();
        String sisa = SisaField.getText().trim();
        
        if(menu.equals("") || harga.equals("") || jumlah.equals("") || sisa.equals("")){
            AlertMessage(1,"Data Harus Diisi");
        } else {
            try {
                String sqlSelect = "SELECT * FROM tb_menu WHERE menu= '" + menu + "' ";
                queryResult = state.executeQuery(sqlSelect);
                int record = 0;
                while (queryResult.next()){
                    record++;
                }
                
                if(record > 0){
                    AlertMessage(1,"Menu Sudah Tersedia");
                } else {
                    String sqlInsert = "INSERT INTO tb_menu VALUES('" + menu + "','" + harga + "','" + jumlah + "','" + sisa + "')";
                    state = conn.createStatement();
                    int result = state.executeUpdate(sqlInsert);
                    
                    if(result > 0){
                        AlertMessage(0,"Tambah Menu Berhasil");
                        colMenu.setText("");
                        colHarga.setText("");
                        colJumlah.setText("");
                        colSisa.setText("");
                        tambahData();
                    }
                }
                
                queryResult.close();
                state.close();
            } catch(SQLException e){
                System.out.println("Gagal tambah data" + e);
            }
        }
    }
    
    @FXML
    private void updateData() throws SQLException{
        
    }
    
    @FXML
    private void deleteData() throws SQLException{
        try {
            String sql = "DELETE FROM tb_menu WHERE menu=" + this.data_menu;
            java.sql.Connection conn = (Connection)koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            AlertMessage(0,"Data Berhasill Dihapus");
            this.connect();
        } catch(SQLException e){
                System.out.print(e);
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
    
}
