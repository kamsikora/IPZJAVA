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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kamil
 */
public class Zadania_sprinty implements Initializable {

    @FXML
    private TableView<Zadanie> tabelaS;
    @FXML
    private TableColumn<Zadanie, String> zadanieS;
    @FXML
    private TableColumn<Zadanie, String> czasS;
    @FXML
    private TableColumn<Zadanie, String> opisS;
    @FXML
    private TableColumn<Zadanie, String> stanS;
    @FXML
    private TableView<Zadanie> tabelaW;
    @FXML
    private TableColumn<Zadanie, String> zadanieW;
    @FXML
    private TableColumn<Zadanie, String> czasW;
    @FXML
    private TableColumn<Zadanie, String> opisW;
    @FXML
    private TableColumn<Zadanie, String> stanW;
    @FXML
    private Button dodaj;
    @FXML
    private Button usun;
    
    private IPZ podstawa;
    
    private final ObservableList<Zadanie> zadanieDataW = FXCollections.observableArrayList();
    public ObservableList<Zadanie> getZadanieDataW() {
        return zadanieDataW;
    }
    private final ObservableList<Zadanie> zadanieDataS = FXCollections.observableArrayList();
    public ObservableList<Zadanie> getZadanieDataS() {
        return zadanieDataS;
    }
    private final ObservableList<Zadanie> zadanieDataW2 = FXCollections.observableArrayList();
    public ObservableList<Zadanie> getZadanieDataW2() {
        return zadanieDataW2;
    }
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    public void setGlowny(IPZ podstawa) throws SQLException {
        this.podstawa=podstawa;   
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM `zadanie_to_projekt` INNER JOIN `zadanie_to_sprint` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie_to_sprint`.`id_zadanie` INNER JOIN `sprint` ON `zadanie_to_sprint`.`id_sprint` = `sprint`.`id` AND `sprint`.`nazwa` = \""+podstawa.getNazwaSprint()+"\" INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` INNER JOIN `stan` ON `zadanie`.`id_stan` = `stan`.`id` INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` = \""+podstawa.getNazwaProjekt()+"\"");
        while(rs.next()) { 
            zadanieDataS.add(new Zadanie(rs.getString("zadanie.nazwa"), rs.getString("czas"), rs.getString("opis"), rs.getString("opis_dlugi"), rs.getString("stan.nazwa"),"")); 
        }
        rs = st.executeQuery("SELECT * FROM `zadanie_to_projekt` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND NOT EXISTS(SELECT * FROM `zadanie_to_sprint` WHERE `zadanie`.`id`=`zadanie_to_sprint`.`id_zadanie`) INNER JOIN `stan` ON `zadanie`.`id_stan` = `stan`.`id` INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` = \""+podstawa.getNazwaProjekt()+"\"");
        while(rs.next()) { 
            zadanieDataW.add(new Zadanie(rs.getString("zadanie.nazwa"), rs.getString("czas"), rs.getString("opis"), rs.getString("opis_dlugi"), rs.getString("stan.nazwa"),"")); 
        }  
        zadanieDataW2.addAll(zadanieDataW);
        for(Zadanie tabP : zadanieDataS) {
            for(Zadanie tabW : zadanieDataW) {
                if(tabW.NazwaProperty().get().equals(tabP.NazwaProperty().get()))
                {
                    getZadanieDataW2().remove(tabW);
                }
            }    
        }
        tabelaW.setItems(getZadanieDataW2());
        tabelaS.setItems(getZadanieDataS());
    }   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        zadanieS.setCellValueFactory(cellData -> cellData.getValue().NazwaProperty());
        czasS.setCellValueFactory(cellData -> cellData.getValue().CzasProperty());
        opisS.setCellValueFactory(cellData -> cellData.getValue().OpisProperty());
        stanS.setCellValueFactory(cellData -> cellData.getValue().StanProperty());
        zadanieW.setCellValueFactory(cellData -> cellData.getValue().NazwaProperty());
        czasW.setCellValueFactory(cellData -> cellData.getValue().CzasProperty());
        opisW.setCellValueFactory(cellData -> cellData.getValue().OpisProperty());
        stanW.setCellValueFactory(cellData -> cellData.getValue().StanProperty());
    }    
    private Zadanie zadanie;
 
     @FXML
    private void dodaj(ActionEvent event) throws SQLException, Exception {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        st.executeUpdate("INSERT INTO `zadanie_to_sprint`(`id_sprint`, `id_zadanie`) VALUES ((SELECT `id_sprint` FROM  `sprint_to_projekt` INNER JOIN  `sprint` ON  `sprint_to_projekt`.`id_sprint` =  `sprint`.`id` AND  `nazwa` = \""+podstawa.getNazwaSprint()+"\" INNER JOIN  `projekt` ON  `sprint_to_projekt`.`id_projekt` =  `projekt`.`id` WHERE `projekt`.`nazwa`= \""+podstawa.getNazwaProjekt()+"\"),(SELECT `id_zadanie` FROM `zadanie_to_projekt` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND  `nazwa` = \""+zadanie.NazwaProperty().get()+"\" INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` =\""+podstawa.getNazwaProjekt()+"\"))");  
        getZadanieDataS().add(zadanie);
        getZadanieDataW2().remove(zadanie); 
    }

    @FXML
    private void usun(ActionEvent event) throws SQLException, Exception {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        st.executeUpdate("DELETE FROM `zadanie_to_sprint` WHERE `id_zadanie` = (SELECT `id_zadanie` FROM `zadanie_to_projekt` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND  `nazwa` = \""+zadanie.NazwaProperty().get()+"\" INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` =\""+podstawa.getNazwaProjekt()+"\")"); 
        getZadanieDataS().remove(zadanie);
        getZadanieDataW2().add(zadanie); 
    }

    @FXML
    private void cofnij(ActionEvent event) throws Exception {
        podstawa.Okno();
    }    

    @FXML
    private void dodawanie(MouseEvent event) {
        usun.setDisable(true);
        if(!tabelaW.getItems().isEmpty()) {
            dodaj.setDisable(false);
        }
        zadanie = tabelaW.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void usuwanie(MouseEvent event) {
        dodaj.setDisable(true);
        if(!tabelaS.getItems().isEmpty()) { 
            usun.setDisable(false);
        }
        zadanie = tabelaS.getSelectionModel().getSelectedItem();
    }
}
