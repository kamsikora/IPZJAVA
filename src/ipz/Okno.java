/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
    }   

    

}
