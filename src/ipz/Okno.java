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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author Kamil
 */
public class Okno {
    
    private final ObservableList<Projekt> projektData = FXCollections.observableArrayList();
    public ObservableList<Projekt> getProjektData() {
        return projektData;
    }
    private final ObservableList<Sprint> sprintData = FXCollections.observableArrayList();
    public ObservableList<Sprint> getSprintData() {
        return sprintData;
    }
    @FXML
    private Label projekt;
    @FXML
    private TableView<Sprint> tabela;
    @FXML
    private TableColumn<Sprint, String> sprint;
    @FXML
    private TableColumn<Sprint, String> dataroz;
    @FXML
    private TableColumn<Sprint, String> datazak;
    @FXML
    private Label info;
    @FXML
    private ComboBox<Projekt> lista;
    
    private IPZ podstawa;

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
        lista.setItems(getProjektData());
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
        public void changed(ObservableValue ov, Projekt oldValue, Projekt newValue)  {
            sprintData.removeAll(sprintData);
            tabela.setItems(getSprintData());
            sprint.setCellValueFactory(cellData -> cellData.getValue().NazwaProperty());
            dataroz.setCellValueFactory(cellData -> cellData.getValue().Data_rozpoczeciaProperty());
            datazak.setCellValueFactory(cellData -> cellData.getValue().Data_zakonczeniaProperty());
            try {
                projekt.setText("Nazwa projektu: "+newValue.getnazwa()+"\nData rozpoczęcia: "+newValue.getdata_rozpoczecia()+" Data zakończenia: "+newValue.getdata_zakonczenia());
                con = DriverManager.getConnection(url, user, password);
                rs = st.executeQuery("SELECT * FROM  `sprint_to_projekt` INNER JOIN  `sprint` ON  `sprint_to_projekt`.`id_sprint` =  `sprint`.`id` INNER JOIN  `projekt` ON  `sprint_to_projekt`.`id_projekt` =  `projekt`.`id` WHERE `projekt`.`nazwa`= \""+newValue.getnazwa()+"\"");
                while(rs.next()) { 
                    sprintData.add(new Sprint(rs.getString("nazwa"),rs.getString("data_rozpoczecia"),rs.getString("data_zakonczenia")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Okno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    }  
}
