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
public class Sprint {
    
    private final StringProperty nazwa;
    private final StringProperty data_rozpoczecia;
    private final StringProperty data_zakonczenia;
    
    public Sprint() {
        this(null, null, null);
    }

    public Sprint(String nazwa, String data_rozpoczecia, String data_zakonczenia) {
        this.nazwa = new SimpleStringProperty(nazwa);
        this.data_rozpoczecia = new SimpleStringProperty(data_rozpoczecia);
        this.data_zakonczenia = new SimpleStringProperty(data_zakonczenia);
    }
 
    public StringProperty NazwaProperty() {
        return nazwa;
    }
    public StringProperty Data_rozpoczeciaProperty() {
        return data_rozpoczecia;
    }
    public StringProperty Data_zakonczeniaProperty() {
        return data_zakonczenia;
    }
    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }
    public void setData_rozpoczecia(String data_rozpoczecia) {
        this.data_rozpoczecia.set(data_rozpoczecia);
    }
    public void setData_zakonczenia(String data_zakonczenia) {
        this.data_zakonczenia.set(data_zakonczenia);
    }
}
