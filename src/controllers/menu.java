/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Asus
 */
public class menu {
    private SimpleStringProperty menu;
    private SimpleStringProperty harga;
    private SimpleStringProperty jumlah;
    private SimpleStringProperty sisa;
    
public menu(String Menu, String Harga, String Jumlah, String Sisa){
    this.menu = new SimpleStringProperty(Menu);
    this.harga = new SimpleStringProperty(Harga);
    this.jumlah = new SimpleStringProperty(Jumlah);
    this.sisa = new SimpleStringProperty(Sisa);
    }

public String getMenu(){
        return menu.get();
    }
    
    public void setMenu(String Menu){
        this.menu = new SimpleStringProperty(Menu);
    }
    
    public String getHarga(){
        return harga.get();
    }
    
    public void setHarga(String Harga){
        this.harga = new SimpleStringProperty(Harga);
    }
    
    public String getJumlah(){
        return jumlah.get();
    }
    
    public void setJumlah(String Jumlah){
        this.jumlah = new SimpleStringProperty(Jumlah);
    }
    
    public String getSisa(){
        return sisa.get();
    }
    
    public void setSisa(String Sisa){
        this.sisa = new SimpleStringProperty(Sisa);
    }
    
}

