package controllers;

import java.sql.*;

public class koneksi {
    private static Connection koneksi;
    
    public static Connection connectDB() throws SQLException{
        if(koneksi == null){
            com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasir","root","");
        }
        return koneksi;
    }
}