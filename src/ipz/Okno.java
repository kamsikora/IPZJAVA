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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Kamil
 */
public class Okno {
    
    private final ObservableList<Projekt> projektData = FXCollections.observableArrayList();
    public ObservableList<Projekt> getprojektData() {
        return projektData;
    }
    
    private IPZ podstawa;
    
    @FXML
    private Label info;
    @FXML
    private ComboBox<Projekt> lista;
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11162352";
    private final String user = "sql11162352";
    private final String password = "wUanP9eU6G";
    
    public Okno() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `projekt`");
        while(rs.next()) { 
            projektData.add(new Projekt(rs.getString("nazwa"), rs.getString("data_rozpoczecia"), rs.getString("data_zakonczenia"))); 
        }
    }
    
    public void Setglowny(IPZ podstawa) throws SQLException {
        this.podstawa=podstawa;
        lista.setItems(getprojektData());
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM  `uzytkownik` INNER JOIN  `rola` ON  `uzytkownik`.`id_rola` =  `rola`.`id` WHERE  `login` = \""+podstawa.getlogin()+"\"");
        while(rs.next()) { 
            info.setText("Zalogowany jako: "+rs.getString("imie")+" "+rs.getString("nazwisko")+"\nRola: "+rs.getString("nazwa"));
        }
    }
    
    @FXML
    private void pokaz(ActionEvent event) throws Exception  {
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
//            Stage stage = (Stage) exit.getScene().getWindow();
//            stage.close();
              podstawa.getstage().close();
        } 
        else 
        {
            alert.close();
        }
    }
    @FXML
    private void dodaj(ActionEvent event) throws Exception {
        podstawa.showDialogProjekt();
        podstawa.Okno();
    }
    
    public void initialize() {

        lista.getSelectionModel().selectFirst();
        lista.setCellFactory(new Callback<ListView<Projekt>, ListCell<Projekt>>() {
        @Override
        public ListCell<Projekt> call(ListView<Projekt> param) {
  
            return new ListCell<Projekt>(){
              @Override
                public void updateItem(Projekt item, boolean empty){
                    super.updateItem(item, empty);
                    if(!empty) {
                        setText(item.getnazwa());
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
        lista.setButtonCell(
        new ListCell<Projekt>() {
        @Override
        public void updateItem(Projekt item, boolean empty) {
            super.updateItem(item, empty); 
            if(!empty) {
                setText(item.getnazwa());
            } 
            else 
            {
                setText(null);
            }
        }
        });
        lista.valueProperty().addListener(new ChangeListener<Projekt>() {
        @Override
        public void changed(ObservableValue ov, Projekt oldValue, Projekt newValue) {
            System.out.println("sad");
        }
        });
    }  
}
