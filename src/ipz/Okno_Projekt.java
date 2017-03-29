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

    private final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11162352?characterEncoding=UTF-8";
    private final String user = "sql11162352";
    private final String password = "wUanP9eU6G";
    
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
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `projekt`(`nazwa`, `data_rozpoczecia`, `data_zakonczenia`) VALUES (\""+nazwa.getText()+"\",\""+start.getValue()+"\",\""+koniec.getValue()+"\")");
            dialog.close();
        }
    }

    @FXML
    private void anuluj(ActionEvent event) {
        dialog.close();
    }
    public void initialize() {
    } 
}
