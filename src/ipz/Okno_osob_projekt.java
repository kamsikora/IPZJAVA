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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Kamil
 */
public class Okno_osob_projekt {

    @FXML
    private TableView<Osoba> tabelaP;
    @FXML
    private TableColumn<Osoba, String> imieP;
    @FXML
    private TableColumn<Osoba, String> nazwiskoP;
    @FXML
    private TableColumn<Osoba, String> emailP;
    @FXML
    private TableColumn<Osoba, String> stanowiskoP;
    @FXML
    private TableView<Osoba> tabelaW;
    @FXML
    private TableColumn<Osoba, String> imieW;
    @FXML
    private TableColumn<Osoba, String> nazwiskoW;
    @FXML
    private TableColumn<Osoba, String> emailW;
    @FXML
    private Button usun;
    @FXML
    private Button dodaj;

    private IPZ podstawa;
    
    private final ObservableList<Osoba> personDataW = FXCollections.observableArrayList();
    public ObservableList<Osoba> getPersonDataW() {
        return personDataW;
    }
    private final ObservableList<Osoba> personDataP = FXCollections.observableArrayList();
    public ObservableList<Osoba> getPersonDataP() {
        return personDataP;
    }
    private final ObservableList<Osoba> personDataW2 = FXCollections.observableArrayList();
    public ObservableList<Osoba> getPersonDataW2() {
        return personDataW2;
    }
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    public Okno_osob_projekt() {
       
    }
        
    public void Setglowny(IPZ podstawa) throws SQLException {
        this.podstawa=podstawa;   
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `uzytkownik_to_projekt` INNER JOIN  `uzytkownik` ON  `uzytkownik_to_projekt`.`id_uzytkownik` =  `uzytkownik`.`id` INNER JOIN  `stanowisko` ON  `uzytkownik_to_projekt`.`id_stanowisko` =  `stanowisko`.`id` INNER JOIN  `projekt` ON  `uzytkownik_to_projekt`.`id_projekt` =  `projekt`.`id` WHERE `projekt`.`nazwa` =\""+podstawa.getNazwaProjekt()+"\"");
        while(rs.next()) { 
            personDataP.add(new Osoba(rs.getString("imie"), rs.getString("nazwisko"), rs.getString("email"), rs.getString("nazwa"), rs.getString("login"), rs.getString("haslo"), rs.getString("stanowisko.nazwa"))); 
        }
        rs = st.executeQuery("SELECT * FROM  `uzytkownik` INNER JOIN  `rola` ON  `uzytkownik`.`id_rola` =  `rola`.`id`");
        while(rs.next()) { 
            personDataW.add(new Osoba(rs.getString("imie"), rs.getString("nazwisko"), rs.getString("email"), rs.getString("nazwa"), rs.getString("login"), rs.getString("haslo"),""));
        }  
        personDataW2.addAll(personDataW);
        for(Osoba tabP : personDataP) {
            for(Osoba tabW : personDataW) {
                if(tabW.getLogin().equals(tabP.getLogin()))
                {
                    personDataW2.remove(tabW);
                }
            }    
        }
        tabelaW.setItems(getPersonDataW2());
        tabelaP.setItems(getPersonDataP());
    }     
    public void initialize() {
        imieW.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoW.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        emailW.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        imieP.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoP.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        emailP.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        stanowiskoP.setCellValueFactory(cellData -> cellData.getValue().stanowiskoProperty());
    }    
    private Osoba osoba;
    @FXML
    private void usuwanie(MouseEvent event) {
        usun.setDisable(false);
        dodaj.setDisable(true);
        osoba = tabelaP.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void dodawanie(MouseEvent event) {
        dodaj.setDisable(false);
        usun.setDisable(true);
        osoba = tabelaW.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void dodaj(ActionEvent event) throws SQLException, Exception {
        List<String> choices = new ArrayList<>();
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `stanowisko`");
        while(rs.next()) { 
            choices.add(rs.getString("nazwa"));
        }
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Wybór stanowiska");
        dialog.setHeaderText("Wybierz stanowisko użytkownika w projekcie");
        dialog.setContentText("Wybierz stanowisko:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `uzytkownik_to_projekt`(`id_projekt`, `id_uzytkownik`, `id_stanowisko`) VALUES ((SELECT `id` FROM  `projekt` WHERE  `nazwa` = \""+podstawa.getNazwaProjekt()+"\"),(SELECT `id` FROM  `uzytkownik` WHERE  `login` = \""+osoba.getLogin()+"\"),(SELECT `id` FROM  `stanowisko` WHERE  `nazwa` = \""+result.get()+"\"))");
        }    
    podstawa.Okno_osob_projekt();        
    }

    @FXML
    private void usun(ActionEvent event) throws SQLException, Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie użytkownika z projektu");
        alert.setHeaderText("Czy napewno chcesz usunąć użytkownika z projektu?");

        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeCancel = new ButtonType("Nie");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            st.executeUpdate("DELETE FROM `uzytkownik_to_projekt` WHERE `id_uzytkownik` = (SELECT `id` FROM  `uzytkownik` WHERE  `login` = \""+osoba.getLogin()+"\")"); 
        } 
        else 
        {
            alert.close();
        }  
        podstawa.Okno_osob_projekt();
    }

    @FXML
    private void cofnij(ActionEvent event) throws Exception {
        podstawa.Okno();
    }
}
