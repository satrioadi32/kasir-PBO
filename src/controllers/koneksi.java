package controllers;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class koneksi {
    static Connection conn=null;
    
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost/db_kasir";
        String user = "root";
        String pass = "";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
