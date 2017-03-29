/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Kamil
 */
public class Okno_osob {
    
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
    
    public Okno_osob() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `uzytkownik` INNER JOIN  `rola` ON  `uzytkownik`.`id_rola` =  `rola`.`id`");
        while(rs.next()) { 
            personData.add(new Osoba(rs.getString("imie"), rs.getString("nazwisko"), rs.getString("email"), rs.getString("nazwa"))); 
        }
    }
    public void Setglowny(IPZ podstawa) {
        this.podstawa=podstawa;
        tabela.setItems(getPersonData());
    }
    @FXML
    private TableView<Osoba> tabela;
    @FXML
    private TableColumn<Osoba, String> imie;
    @FXML
    private TableColumn<Osoba, String> nazwisko;
    @FXML
    private TableColumn<Osoba, String> email;
    @FXML
    private TableColumn<Osoba, String> ranga;
    
    @FXML
    private void cofnij(ActionEvent event) throws Exception {
        podstawa.Okno();
    }

    @FXML
    private void dodaj(ActionEvent event) throws Exception {
        podstawa.showDialogRejestracja();
        podstawa.Okno_osob();
    }
    public void initialize() {
        imie.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        nazwisko.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        ranga.setCellValueFactory(cellData -> cellData.getValue().rangaProperty());
    } 
}
