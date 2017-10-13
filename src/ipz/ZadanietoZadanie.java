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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

/**
 * FXML Controller class
 *
 * @author Wiesław-J
 */
public class ZadanietoZadanie implements Initializable {

    @FXML
    private Label nazwa;
    @FXML
    private Label czas;
    @FXML
    private Label opis;
    @FXML
    private Label opisD;
    @FXML
    private Label sprint;
    @FXML
    private Label stan;
    @FXML
    private TextArea komentarz;
    @FXML
    private ListView<String> komentarze;
    @FXML
    private ToggleButton zmianaStanu;

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    private final ObservableList<String> komentarzData = FXCollections.observableArrayList();
    public ObservableList<String> getKomentarzData() {
        return komentarzData;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        komentarze.setItems(getKomentarzData());
    }    
    
    private IPZ podstawa;
    public void setGlowny(IPZ podstawa) throws SQLException {
        this.podstawa=podstawa;
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM `zadanie_to_projekt` LEFT OUTER JOIN `zadanie_to_sprint` ON `zadanie_to_projekt`.`id_zadanie`= `zadanie_to_sprint`.`id_zadanie` LEFT OUTER JOIN `sprint` ON `zadanie_to_sprint`.`id_sprint` = `sprint`.`id` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND `zadanie`.`nazwa` = \""+podstawa.getNazwaZadanie()+"\" INNER JOIN `stan` ON `zadanie`.`id_stan` = `stan`.`id` INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` = \""+podstawa.getNazwaProjekt()+"\"");
        while(rs.next()) { 
            nazwa.setText(rs.getString("zadanie.nazwa"));
            czas.setText(rs.getString("czas"));
            opis.setText(rs.getString("opis"));
            opisD.setText(rs.getString("opis_dlugi"));
            stan.setText(rs.getString("stan.nazwa"));
            sprint.setText(rs.getString("sprint.nazwa"));
        }
        rs = st.executeQuery("SELECT * FROM `zadanie_to_projekt` LEFT OUTER JOIN `komentarz` ON `zadanie_to_projekt`.`id_zadanie`= `komentarz`.`id_zadanie` INNER JOIN `uzytkownik` ON `komentarz`.`id_uzytkownik` = `uzytkownik`.`id` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND `zadanie`.`nazwa` = \""+podstawa.getNazwaZadanie()+"\" INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` = \""+podstawa.getNazwaProjekt()+"\"");
        while(rs.next()) { 
            komentarzData.add(rs.getString("imie")+" "+rs.getString("nazwisko")+"  |  "+rs.getString("data_czas")+"  |  "+rs.getString("tresc"));
        }
        if(stan.getText().equals("Do wykonania") || stan.getText().equals("Zakończone")) {
            zmianaStanu.setText("Rozpocznij");
        }
        else
        {
            zmianaStanu.setSelected(true);
            zmianaStanu.setText("Zakończ");
        }
    }

    @FXML
    private void cofnij(ActionEvent event) throws Exception {
        podstawa.Okno();
    }

    @FXML
    private void dodaj(ActionEvent event) throws SQLException {
        if(komentarz.getText().trim().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(podstawa.getStage());
            alert.setTitle("Puste pola");
            alert.setHeaderText("Nie wszystkie pola są wypełnione");
            alert.setContentText("Proszę wypełnić wszystkie pola");
            alert.showAndWait();
        }
        else
        {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `komentarz` (`tresc`, `id_zadanie`, `id_uzytkownik`) VALUES (\""+komentarz.getText()+"\", (SELECT `zadanie`.`id` FROM `zadanie_to_projekt` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND `zadanie`.`nazwa` = \""+podstawa.getNazwaZadanie()+"\" INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` = \""+podstawa.getNazwaProjekt()+"\"), (SELECT `id` FROM  `uzytkownik` WHERE `login` = \""+podstawa.getLogin()+"\"))"); 
            komentarzData.removeAll(komentarzData);
            rs = st.executeQuery("SELECT * FROM `zadanie_to_projekt` LEFT OUTER JOIN `komentarz` ON `zadanie_to_projekt`.`id_zadanie`= `komentarz`.`id_zadanie` INNER JOIN `uzytkownik` ON `komentarz`.`id_uzytkownik` = `uzytkownik`.`id` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND `zadanie`.`nazwa` = \""+podstawa.getNazwaZadanie()+"\" INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` = \""+podstawa.getNazwaProjekt()+"\"");
            while(rs.next()) { 
                getKomentarzData().add(rs.getString("imie")+" "+rs.getString("nazwisko")+"  |  "+rs.getString("data_czas")+"  |  "+rs.getString("tresc"));
            } 
        }
    }

    @FXML
    private void zmiana(ActionEvent event) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        if(zmianaStanu.isSelected())
        {
            zmianaStanu.setText("Zakończ");
            stan.setText("W trakcie"); 
        }
        else
        {
            zmianaStanu.setText("Rozpocznij");
            stan.setText("Zakończone");
        } 
        st.executeUpdate("UPDATE `zadanie` SET `id_stan` = (SELECT `id` FROM `stan` WHERE `nazwa` = \""+stan.getText()+"\") WHERE `id` = (SELECT * FROM(SELECT `id_zadanie` FROM `zadanie_to_projekt` INNER JOIN `zadanie` ON `zadanie_to_projekt`.`id_zadanie` = `zadanie`.`id` AND  `nazwa` = \""+podstawa.getNazwaZadanie()+"\" INNER JOIN `projekt` ON `zadanie_to_projekt`.`id_projekt` = `projekt`.`id` WHERE `projekt`.`nazwa` =\""+podstawa.getNazwaProjekt()+"\")tmp)");
    }
}
