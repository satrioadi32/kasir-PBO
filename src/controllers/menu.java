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
    private SimpleIntegerProperty harga;
    private SimpleIntegerProperty jumlah;
    private SimpleIntegerProperty sisa;
    
public menu(String Menu, int Harga, int Jumlah, int Sisa){
    this.menu = new SimpleStringProperty(Menu);
    this.harga = new SimpleIntegerProperty(Harga);
    this.jumlah = new SimpleIntegerProperty(Jumlah);
    this.sisa = new SimpleIntegerProperty(Sisa);
    }

public String getMenu(){
        return menu.get();
    }
    
    public void setMenu(String Menu){
        this.menu = new SimpleStringProperty(Menu);
    }
    
    public double getHarga(){
        return harga.get();
    }
    
    public void setHarga(int Harga){
        this.harga = new SimpleIntegerProperty(Harga);
    }
    
    public int getJumlah(){
        return jumlah.get();
    }
    
    public void setJumlah(int Jumlah){
        this.jumlah = new SimpleIntegerProperty(Jumlah);
    }
    
    public int getSisa(){
        return sisa.get();
    }
    
    public void setSisa(int Sisa){
        this.sisa = new SimpleIntegerProperty(Sisa);
    }
    
}

