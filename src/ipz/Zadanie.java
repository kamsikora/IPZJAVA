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
    private final StringProperty Stan;

    public Zadanie(String Nazwa, String Czas, String Opis, String Stan) {
        this.Nazwa = new SimpleStringProperty(Nazwa);
        this.Czas = new SimpleStringProperty(Czas);
        this.Opis = new SimpleStringProperty(Opis);
        this.Stan = new SimpleStringProperty(Stan);
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
    public StringProperty StanProperty() {
        return Stan;
    }
}

