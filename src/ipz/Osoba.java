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
public class Osoba {
    private final StringProperty imie;
    private final StringProperty nazwisko;
    private final StringProperty email;
    private final StringProperty ranga;
    private final StringProperty login;
    private final StringProperty haslo;
    private final StringProperty stanowisko;
    
    public Osoba(String imie, String nazwisko, String email, String ranga, String login, String haslo, String stanowisko) {
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.email = new SimpleStringProperty(email);
        this.ranga = new SimpleStringProperty(ranga);
        this.login = new SimpleStringProperty(login);
        this.haslo = new SimpleStringProperty(haslo);
        this.stanowisko = new SimpleStringProperty(stanowisko);
    }
 
    public StringProperty imieProperty() {
        return imie;
    }
    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }
    public StringProperty emailProperty() {
        return email;
    }
    public StringProperty rangaProperty() {
        return ranga;
    }
    public StringProperty stanowiskoProperty() {
        return stanowisko;
    }
    public String getLogin() {
        return login.get();
    }
    public String getImie() {
        return imie.get();
    }    
    public String getNazwisko() {
        return nazwisko.get();
    }
    public String getEmail() {
        return email.get();
    }
    public String getRanga() {
        return ranga.get();
    }
     public String getHaslo() {
        return haslo.get();
    }
//    public void setLastName(String fName) {
//        nazwisko.set(fName);
//    }
//    public void setFirstName(String fName) {
//        imie.set(fName);
//    }
//    public void setEmail(String fName) {
//        email.set(fName);
//    }    
}
