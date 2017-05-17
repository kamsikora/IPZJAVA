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
public class Okno_Sprintu {
    
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

    private final String url = "jdbc:mysql://mysql8.db4free.net:3307/ipzdb?characterEncoding=UTF-8&useSSL=false";
    private final String user = "ipzuser";
    private final String password = "ipzpassword";
    
    private boolean okClicked = false;
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    private Sprint sprint;
    public void setSprint(Sprint sprint) {
        this.sprint=sprint;
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }
    
    private IPZ controller;

    public void setController(IPZ controller ) {
        this.controller = controller;
    }
    
    @FXML
    private void ok(ActionEvent event) throws SQLException {
        if(nazwa.getText().trim().equals("") || start.getValue()==null || koniec.getValue()==null)
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
            rs = st.executeQuery("SELECT * FROM `sprint_to_projekt` INNER JOIN `projekt` ON `sprint_to_projekt`.`id_projekt` = `projekt`.`id` AND `projekt`.`nazwa` = \""+controller.getNazwaProjekt()+"\" INNER JOIN `sprint` ON `sprint_to_projekt`.`id_sprint` = `sprint`.`id` WHERE  `sprint`.`nazwa` = \""+nazwa.getText()+"\"");
            if(rs.absolute(1)) { 
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialog);
                alert.setTitle("Nazwa sprintu");
                alert.setHeaderText("Sprint o podanej nazwie jest już w projekcie.");
                alert.setContentText("Proszę podać inną nazwę sprintu");
                alert.showAndWait();
            }
            else 
            {
               con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                st.executeUpdate("INSERT INTO `sprint`(`nazwa`, `data_rozpoczecia`, `data_zakonczenia`) VALUES (\""+nazwa.getText()+"\",\""+start.getValue()+"\",\""+koniec.getValue()+"\")");
                st.executeUpdate("SET @id_sprint = LAST_INSERT_ID()");
                st.executeUpdate("INSERT INTO `sprint_to_projekt` (`id_projekt`, `id_sprint`) VALUES((SELECT `id` FROM  `projekt` WHERE  `nazwa` = \""+controller.getNazwaProjekt()+"\"),@id_sprint)");
                sprint.setNazwa(nazwa.getText());
                sprint.setData_rozpoczecia(start.getValue().toString());
                sprint.setData_zakonczenia(koniec.getValue().toString());
                okClicked = true;
                dialog.close(); 
            }
        }
    }

    @FXML
    private void anuluj(ActionEvent event) {
        dialog.close();
    }
    public void initialize() {
    } 
}