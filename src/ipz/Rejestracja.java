/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Kamil
 */
public class Rejestracja {

    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField login;
    @FXML
    private TextField email;
    @FXML
    private PasswordField haslo;
    @FXML
    private PasswordField haslo2;
    
    private Stage dialog;
    
    private Connection con = null;
    private Statement st = null;

    private final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11162352?characterEncoding=UTF-8";
    private final String user = "sql11162352";
    private final String password = "wUanP9eU6G";
    @FXML
    private AnchorPane anchor;
    
    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    }   
    @FXML
    private void ok(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(haslo.getText().equals(haslo2.getText())) {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(haslo.getText()));
            String str = String.format("%032x", new BigInteger(1, md5.digest()));
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `uzytkownik`(`imie`, `nazwisko`, `login`, `email`, `haslo`) VALUES (\""+imie.getText()+"\",\""+nazwisko.getText()+"\",\""+login.getText()+"\",\""+email.getText()+"\",\""+str+"\")");
            dialog.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialog);
            alert.setTitle("Błąd hasła");
            alert.setHeaderText("Hasła nie zgadzają się");
            alert.setContentText("Proszę podać ponownie hasła");
            alert.showAndWait();
        } 
    }

    @FXML
    private void anuluj(ActionEvent event) {
        dialog.close();
    }

    
}
