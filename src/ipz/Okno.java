/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Kamil
 */
public class Okno {
    
    private final ObservableList<Osoba> personData = FXCollections.observableArrayList();
    private IPZ podstawa;
    
    public ObservableList<Osoba> getPersonData() {
        return personData;
    }
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11162352";
    private final String user = "sql11162352";
    private final String password = "wUanP9eU6G";
    
    public Okno() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT `imie`, `nazwisko`, `email` FROM `uzytkownik`");
        while(rs.next()) { 
            personData.add(new Osoba(rs.getString("imie"), rs.getString("nazwisko"), rs.getString("email"))); 
        }
        rs.close();  
    }
    public void Setglowny(IPZ podstawa) {
        this.podstawa=podstawa;
        tabela.setItems(getPersonData());
        imie.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        nazwisko.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }
    @FXML
    private TableView<Osoba> tabela;
    @FXML
    private TableColumn<Osoba, String> imie;
    @FXML
    private TableColumn<Osoba, String> nazwisko;
    @FXML
    private TableColumn<Osoba, String> email;
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
    } 

}
