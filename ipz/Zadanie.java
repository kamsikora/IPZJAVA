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
    private final StringProperty Czas;
    private final StringProperty Opis;
    private final StringProperty OpisD;
    private final StringProperty Stan;
    private final StringProperty Sprint;

    public Zadanie(String Nazwa, String Czas, String Opis, String OpisD, String Stan, String Sprint) {
        this.Nazwa = new SimpleStringProperty(Nazwa);
        this.Czas = new SimpleStringProperty(Czas);
        this.Opis = new SimpleStringProperty(Opis);
        this.OpisD = new SimpleStringProperty(OpisD);
        this.Stan = new SimpleStringProperty(Stan);
        this.Sprint = new SimpleStringProperty(Sprint);
    }

    public Zadanie() {
        this(null, null, null, null, null, null);
    }
 
    public StringProperty NazwaProperty() {
        return Nazwa;
    }
    public StringProperty CzasProperty() {
        return Czas;
    }
    public StringProperty OpisProperty() {
        return Opis;
    }
    public StringProperty OpisDProperty() {
        return OpisD;
    }
    public StringProperty StanProperty() {
        return Stan;
    }
    public StringProperty SprintProperty() {
        return Sprint;
    }
    public void setNazwa(String Nazwa) {
        this.Nazwa.set(Nazwa);
    }
    public void setCzas(String Czas) {
        this.Czas.set(Czas);
    }
    public void setOpis(String Opis) {
        this.Opis.set(Opis);
    }
    public void setOpisD(String OpisD) {
        this.OpisD.set(OpisD);
    }
    public void setStan(String Stan) {
        this.Stan.set(Stan);
    }
}

