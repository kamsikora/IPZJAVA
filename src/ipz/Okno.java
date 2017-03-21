/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Kamil
 */
public class Okno {
    
    private IPZ podstawa;
    
    @FXML
    private Button exit;
    
    public void Setglowny(IPZ podstawa) {
        this.podstawa=podstawa;
    }
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
