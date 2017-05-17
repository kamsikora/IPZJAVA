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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 *
 * @author Kamil
 */
public class Projekty_uzytkownicy {
    
    private IPZ podstawa;
    @FXML
    private Label info;
    
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
        rs = st.executeQuery("SELECT * FROM  `uzytkownik` INNER JOIN  `rola` ON  `uzytkownik`.`id_rola` =  `rola`.`id` WHERE  `login` = \""+podstawa.getLogin()+"\"");
        while(rs.next()) { 
            info.setText("Zalogowany jako: "+rs.getString("imie")+" "+rs.getString("nazwisko")+"\nRola: "+rs.getString("nazwa"));
        }
    }
    public void initialize()  {
    }    

    @FXML
    private void projekty(ActionEvent event) throws Exception {
        podstawa.Okno();
    }

    @FXML
    private void uzytkownicy(ActionEvent event) throws Exception {
        podstawa.Okno_osob();
    }

    @FXML
    private void wyjscie(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zamykanie aplikacji");
        alert.setHeaderText("Czy chcesz wyłączyć program?");

        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeCancel = new ButtonType("Nie");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
//            Stage stage = (Stage) exit.getScene().getWindow();
//            stage.close();
              podstawa.getStage().close();
        } 
        else 
        {
            alert.close();
        }
    }
}
