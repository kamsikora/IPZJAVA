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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Kamil
 */
public class Okno_osob {
    
    private final ObservableList<Osoba> personData = FXCollections.observableArrayList();
    public ObservableList<Osoba> getOsobaData() {
        return personData;
    }
    
    private IPZ podstawa;

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    public Okno_osob() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `uzytkownik` INNER JOIN  `rola` ON  `uzytkownik`.`id_rola` =  `rola`.`id`");
        while(rs.next()) { 
            personData.add(new Osoba(rs.getString("imie"), rs.getString("nazwisko"), rs.getString("email"), rs.getString("nazwa"), rs.getString("login"), rs.getString("haslo"),"")); 
        }
    }
    
    public void setGlowny(IPZ podstawa) {
        this.podstawa=podstawa;
        tabela.setItems(getOsobaData());
    }
    @FXML
    private Button usun;
    @FXML
    private Button edycja;
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
        podstawa.Projekty_uzytkownicy();
    }

    @FXML
    private void dodaj(ActionEvent event) throws Exception {
        Osoba tempOsoba = new Osoba();
        boolean okClicked = podstawa.showDialogRejestracja(tempOsoba);
        if (okClicked) {
            getOsobaData().add(tempOsoba);
        }
    }
    public void initialize() {
        imie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        ranga.setCellValueFactory(cellData -> cellData.getValue().rangaProperty());
    } 

    @FXML
    private void usun(ActionEvent event) throws SQLException, Exception {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `uzytkownik_to_projekt` INNER JOIN  `stanowisko` ON  `uzytkownik_to_projekt`.`id_stanowisko` =  `stanowisko`.`id` INNER JOIN  `projekt` ON  `uzytkownik_to_projekt`.`id_projekt` =  `projekt`.`id` INNER JOIN  `uzytkownik` ON  `uzytkownik_to_projekt`.`id_uzytkownik` =  `uzytkownik`.`id` WHERE `login` =\""+osoba.getLogin()+"\"");
        if(rs.absolute(1)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuwanie użytkownika");
            alert.setHeaderText("Użytkownik "+osoba.getImie()+" "+osoba.getNazwisko()+" jest przypisany do projektu, czy napewno chcesz usunąć użytkownika?");

            ButtonType buttonTypeOne = new ButtonType("Tak");
            ButtonType buttonTypeCancel = new ButtonType("Nie");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                st.executeUpdate("DELETE FROM `uzytkownik_to_projekt` WHERE `id_uzytkownik` = (SELECT `id` FROM `uzytkownik` WHERE `login` =\""+osoba.getLogin()+"\")"); 
                st.executeUpdate("DELETE FROM `uzytkownik` WHERE `login` =\""+osoba.getLogin()+"\""); 
                getOsobaData().remove(osoba);
            } 
            else 
            {
                alert.close();
            }  
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuwanie użytkownika");
            alert.setHeaderText("Czy napewno chcesz usunąć użytkownika?");

            ButtonType buttonTypeOne = new ButtonType("Tak");
            ButtonType buttonTypeCancel = new ButtonType("Nie");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                st.executeUpdate("DELETE FROM `uzytkownik` WHERE `login` =\""+osoba.getLogin()+"\""); 
                getOsobaData().remove(osoba);
            } 
            else 
            {
                alert.close();
            }  
        }
    }
    private Osoba osoba;
    @FXML
    private void usuwanie(MouseEvent event) {
        usun.setDisable(false);
        edycja.setDisable(false);
        osoba = tabela.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void edytuj(ActionEvent event) throws Exception {
       podstawa.showDialogEdycja(osoba);
    }
}
