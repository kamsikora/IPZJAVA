/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipz;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author Kamil
 */
public class IPZ extends Application {
    
    private Stage stage;
    public Stage getstage() {
        return stage;
    } 
    
    private String login;
    public String getlogin() {
        return login;
    }
    public void setlogin(String login) {
        this.login=login;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        FXMLDocumentController controller = loader.getController();
        controller.Setglowny(this);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rejestracja.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Rejestracja");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        Rejestracja controller = loader.getController();
        controller.setDialog(dialogStage);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
    }
       
    public void Okno() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Okno controller = loader.getController();
        controller.Setglowny(this);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Okno_osob() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno_osob.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Okno_osob controller = loader.getController();
        controller.Setglowny(this);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
}
