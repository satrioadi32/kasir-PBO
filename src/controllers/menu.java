/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Asus
 */
public class menu {
   private final StringProperty Menu;
   private final StringProperty Harga;
   private final StringProperty Jumlah;
   private final StringProperty Sisa;

   
   public menu(String Menu, String Harga, String Jumlah, String Sisa){
       this.Menu = new SimpleStringProperty(Menu);
       this.Harga = new SimpleStringProperty(Harga);
       this.Jumlah = new SimpleStringProperty(Jumlah);
       this.Sisa = new SimpleStringProperty(Sisa);
   }

    public String getMenu(){
        return Menu.get();
    }
    public StringProperty menuProperty() {
        return Menu;
    }
    public void setMenu(String menu){
        this.Menu.set(menu);
    }
    
    
    
    public String getHarga(){
        return Harga.get();
    }
    public StringProperty hargaProperty() {
        return Harga;
    }
    public void setHarga(String harga){
        this.Harga.set(harga);
    }
    
    
    
    public String getJumlah(){
        return Jumlah.get();
    }
    public StringProperty jumlahProperty() {
        return Jumlah;
    }
    public void setJumlah(String jumlah){
        this.Harga.set(jumlah);
    }
    
    
    
    public String getSisa(){
        return Sisa.get();
    }
    public StringProperty sisaProperty() {
        return Sisa;
    }
    public void setSisa(String sisa){
        this.Sisa.set(sisa);
    }
    
}