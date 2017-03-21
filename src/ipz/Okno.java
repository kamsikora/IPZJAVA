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

/**
 *
 * @author Kamil
 */
public class Okno {
    
    private IPZ podstawa;
    
    public void Setglowny(IPZ podstawa) {
        this.podstawa=podstawa;
    }
    @FXML
    private void pokaz(ActionEvent event) throws Exception {
        podstawa.Okno_osob();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
    }   
}
