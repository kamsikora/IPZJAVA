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
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    
    public Osoba(String fName, String lName, String email) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }
 
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public StringProperty emailProperty() {
        return email;
    }
    
    public String getFirstName() {
        return firstName.get();
    }    
    
    public void setFirstName(String fName) {
        firstName.set(fName);
    }
 
    public String getLastName() {
        return lastName.get();
    }
 
    public void setLastName(String fName) {
        lastName.set(fName);
    }
 
    public String getEmail() {
        return email.get();
    }
 
    public void setEmail(String fName) {
        email.set(fName);
    }    
}
