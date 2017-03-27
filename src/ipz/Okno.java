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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Kamil
 */
public class Okno {
    
    private IPZ podstawa;
    @FXML
    private Button exit;
    
    @FXML
    private Label info;

    public void Setglowny(IPZ podstawa) throws SQLException {
        this.podstawa=podstawa;
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `uzytkownik` INNER JOIN  `rola` ON  `uzytkownik`.`id_rola` =  `rola`.`id` WHERE  `login` = \""+podstawa.getlogin()+"\"");
        while(rs.next()) { 
            info.setText("Zalogowany jako: "+rs.getString("imie")+" "+rs.getString("nazwisko")+"\nRola: "+rs.getString("nazwa"));
        }
    }
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11162352";
    private final String user = "sql11162352";
    private final String password = "wUanP9eU6G";
    
    @FXML
    private void pokaz(ActionEvent event) throws Exception {
        podstawa.Okno_osob();
    }
    @FXML
    private void wyjscie(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Zamykanie aplikacji");
        alert.setHeaderText("Czy chcesz wyłączyć program?");

        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeCancel = new ButtonType("Nie");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        } 
        else 
        {
            alert.close();
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
    }   
}
