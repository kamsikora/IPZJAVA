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

    private final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11162352";
    private final String user = "sql11162352";
    private final String password = "wUanP9eU6G";
    
    private IPZ podstawa;
    public void Setglowny(IPZ podstawa) {
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
        while(rs.next()) { 
            if(str.equals(rs.getString(1)))
            {
                podstawa.setlogin(login.getText());
                podstawa.Okno();
            } 
            else
            {
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(podstawa.getstage());
                alert.setTitle("Błąd logowania");
                alert.setHeaderText("Złe hasło lub login");
                alert.setContentText("Proszę podać ponownie hasło");
                alert.showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
