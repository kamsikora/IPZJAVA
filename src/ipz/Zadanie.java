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
public class Zadanie {
    
    private final StringProperty Nazwa;
    private final StringProperty Data_rozpoczecia;
    private final StringProperty Data_zakonczenia;

    public Zadanie(String Nazwa, String Data_rozpoczecia, String Data_zakonczenia) {
        this.Nazwa = new SimpleStringProperty(Nazwa);
        this.Data_rozpoczecia = new SimpleStringProperty(Data_rozpoczecia);
        this.Data_zakonczenia = new SimpleStringProperty(Data_zakonczenia);
    }
 
    public StringProperty NazwaProperty() {
        return Nazwa;
    }
    public StringProperty Data_rozpoczeciaProperty() {
        return Data_rozpoczecia;
    }
    public StringProperty Data_zakonczeniaProperty() {
        return Data_zakonczenia;
    }
}

