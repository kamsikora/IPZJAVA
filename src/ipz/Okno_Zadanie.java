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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Kamil
 */
public class Okno_Zadanie {

    @FXML
    private TextField nazwa;
    @FXML
    private TextField czas;
    @FXML
    private TextField opis;
    @FXML
    private TextArea opisD;
    
    private Stage dialog;
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    private boolean okClicked = false;
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }
    
    private Zadanie zadanie;
    public void setZadanie(Zadanie zadanie) {
        this.zadanie=zadanie;
    }
    
    private IPZ controller;

    public void setController(IPZ controller ) {
        this.controller = controller;
    }
    
    public void initialize() {
    }    

    @FXML
    private void ok(ActionEvent event) throws SQLException {
        if(nazwa.getText().trim().equals("") || czas.getText().trim().equals("") || opis.getText().trim().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialog);
            alert.setTitle("Puste pola");
            alert.setHeaderText("Nie wszystkie pola są wypełnione");
            alert.setContentText("Proszę wypełnić wszystkie pola");
            alert.showAndWait();
        }
        else
        {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `zadanie`(`nazwa`, `czas`, `opis`, `opis_dlugi`) VALUES (\""+nazwa.getText()+"\",\""+czas.getText()+"\",\""+opis.getText()+"\",\""+opisD.getText()+"\")");
            st.executeUpdate("SET @id_zadanie = LAST_INSERT_ID()");
            st.executeUpdate("INSERT INTO `zadanie_to_projekt` (`id_projekt`, `id_zadanie`) VALUES((SELECT `id` FROM  `projekt` WHERE  `nazwa` = \""+controller.getNazwaProjekt()+"\"),@id_zadanie)");
            zadanie.setNazwa(nazwa.getText());
            zadanie.setCzas(czas.getText()+" h");
            zadanie.setOpis(opis.getText());
            zadanie.setOpisD(opisD.getText());
            zadanie.setStan("Do wykonania");
            okClicked = true;
            dialog.close();
        }
    }

    @FXML
    private void anuluj(ActionEvent event) {
        dialog.close();
    } 
}
