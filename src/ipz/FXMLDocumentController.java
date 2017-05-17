/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 *
 * @author Kamil
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private PasswordField haslo;
    @FXML
    private TextField login;
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    private IPZ podstawa;
    public void setGlowny(IPZ podstawa) {
        this.podstawa=podstawa;
        
    }
    @FXML
    private void logowanie(ActionEvent event) throws SQLException, Exception {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT `haslo` FROM `uzytkownik` WHERE `login` = \""+login.getText()+"\"");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(haslo.getText()));
        String str = String.format("%032x", new BigInteger(1, md5.digest()));
        if(!rs.next()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(podstawa.getStage());
            alert.setTitle("Błąd logowania");
            alert.setHeaderText("Zły login");
            alert.setContentText("Proszę podać ponownie login");
            alert.showAndWait();
        } else { 
            do {
                if(str.equals(rs.getString(1)))
                {
                    podstawa.setLogin(login.getText());
                    podstawa.Projekty_uzytkownicy();
                }
                else
                {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.initOwner(podstawa.getStage());
                    alert.setTitle("Błąd logowania");
                    alert.setHeaderText("Złe hasło");
                    alert.setContentText("Proszę podać ponownie hasło");
                    alert.showAndWait();
                }    
            } while (rs.next());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
