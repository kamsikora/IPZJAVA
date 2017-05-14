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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Kamil
 */
public class Okno_Projekt {

    @FXML
    private TextField nazwa;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker koniec;
    
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
    
    private Projekt projekt;
    public void setProjekt(Projekt projekt) {
        this.projekt=projekt;
    }
    
    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    @FXML
    private void ok(ActionEvent event) throws SQLException {
        if(nazwa.getText().trim().equals("") || start.getValue()==null)
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
            if(koniec.getValue()==null) 
            {
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                st.executeUpdate("INSERT INTO `projekt`(`nazwa`, `data_rozpoczecia`) VALUES (\""+nazwa.getText()+"\",\""+start.getValue()+"\")");
                projekt.setNazwa(nazwa.getText());
                projekt.setData_rozpoczecia(start.getValue().toString());
                projekt.setData_zakonczenia("null");
                okClicked = true;
                dialog.close();
            }
            else 
            {
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                st.executeUpdate("INSERT INTO `projekt`(`nazwa`, `data_rozpoczecia`, `data_zakonczenia`) VALUES (\""+nazwa.getText()+"\",\""+start.getValue()+"\",\""+koniec.getValue()+"\")");
                projekt.setNazwa(nazwa.getText());
                projekt.setData_rozpoczecia(start.getValue().toString());
                projekt.setData_zakonczenia(koniec.getValue().toString());
                okClicked = true;
                dialog.close();
            }
        }
    }

    @FXML
    private void anuluj(ActionEvent event) {
        dialog.close();
    }
    public void initialize() {
    } 
}
