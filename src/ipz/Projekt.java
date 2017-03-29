/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Kamil
 */
public class Projekt {
    private final StringProperty nazwa;
    private final StringProperty data_rozpoczecia;
    private final StringProperty data_zakonczenia;
    
    public Projekt(String nazwa, String data_rozpoczecia, String data_zakonczenia) {
        this.nazwa = new SimpleStringProperty(nazwa);
        this.data_rozpoczecia = new SimpleStringProperty(data_rozpoczecia);
        this.data_zakonczenia = new SimpleStringProperty(data_zakonczenia);
    }
 
    public String getnazwa() {
        return nazwa.get();
    }
    public String getdata_rozpoczecia() {
        return data_rozpoczecia.get();
    }
    public String getdata_zakonczenia() {
        return data_zakonczenia.get();
    }
}
