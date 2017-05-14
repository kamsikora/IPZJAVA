/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    @FXML
    private ComboBox<String> rola;
    
    private Stage dialog;
    
    private boolean okClicked = false;
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    private Osoba osoba;
    public void setOsoba(Osoba osoba) {
        this.osoba=osoba;
    }
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    public void setDialog(Stage dialog) throws SQLException {
        this.dialog = dialog;
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `rola`");
        while(rs.next()) { 
           rola.getItems().addAll(rs.getString("nazwa"));
        }
    }
    
    @FXML
    private void ok(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(imie.getText().trim().equals("") || nazwisko.getText().trim().equals("") || login.getText().trim().equals("") || email.getText().trim().equals("") || haslo.getText().trim().equals("") || haslo2.getText().trim().equals("") || rola.getValue() == null)
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
            if(email.getText().contains("@") && email.getText().contains("."))
            {
                if(haslo.getText().equals(haslo2.getText())) {
                    con = DriverManager.getConnection(url, user, password);
                    st = con.createStatement();
                    rs = st.executeQuery("SELECT * FROM `uzytkownik` WHERE `login` = \""+login.getText()+"\"");
                    if(rs.absolute(1)) { 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.initOwner(dialog);
                        alert.setTitle("Login zajęty");
                        alert.setHeaderText("Login jest już zajęty");
                        alert.setContentText("Proszę podać inny login");
                        alert.showAndWait();
                    }
                    else
                    {
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        md5.update(StandardCharsets.UTF_8.encode(haslo.getText()));
                        String str = String.format("%032x", new BigInteger(1, md5.digest()));
                        con = DriverManager.getConnection(url, user, password);
                        st = con.createStatement();
                        st.executeUpdate("INSERT INTO `uzytkownik`(`imie`, `nazwisko`, `login`, `email`, `haslo`, `id_rola`) VALUES (\""+imie.getText()+"\",\""+nazwisko.getText()+"\",\""+login.getText()+"\",\""+email.getText()+"\",\""+str+"\",(SELECT `id` FROM  `rola` WHERE `nazwa` = \""+rola.getValue()+"\"))");
                        osoba.setImie(imie.getText());
                        osoba.setNazwisko(nazwisko.getText());
                        osoba.setLogin(login.getText());
                        osoba.setEmail(email.getText());
                        osoba.setRanga(rola.getValue());
                        okClicked = true;
                        dialog.close();
                    }
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
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialog);
                alert.setTitle("Błąd w adresie email");
                alert.setHeaderText("Adres email nie zgadzają się");
                alert.setContentText("Proszę podać ponownie adres email");
                alert.showAndWait();
            }
                
        }
    }

    @FXML
    private void anuluj(ActionEvent event) {
        dialog.close();
    }
    public void initialize() { 
        rola.getSelectionModel().selectFirst();
        rola.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> param) {
  
            return new ListCell<String>(){
              @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(!empty) {
                        setText(item);
                        setGraphic(null);
                    } 
                    else 
                    {
                        setText(null);
                    }
                }
           };
        }
        });
        rola.setButtonCell(
        new ListCell<String>() {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty); 
            if(!empty) {
                setText(item);
            } 
            else 
            {
                setText(null);
            }
        }
        });
        rola.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue ov, String oldValue, String newValue)  {
        }
        });
    } 
}