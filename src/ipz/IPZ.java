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

    public Stage getStage() {
        return stage;
    } 
    
    private String login;
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login=login;
    }

    private String NazwaProjekt;
    public String getNazwaProjekt() {
        return NazwaProjekt;
    } 
    public void setNazwaProjekt(String NazwaProjekt) {
        this.NazwaProjekt=NazwaProjekt; 
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
    
    public void Okno() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Okno controller = loader.getController();
        controller.Setglowny(this);
        
        stage.hide();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    public void Okno_osob_projekt() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno_osob_projekt.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Okno_osob_projekt controller = loader.getController();
        controller.Setglowny(this);
        
        stage.hide();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    
    public void Okno_osob() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno_osob.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Okno_osob controller = loader.getController();
        controller.Setglowny(this);
        
        stage.hide();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    
    public void Projekty_uzytkownicy() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Projekty_uzytkownicy.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Projekty_uzytkownicy controller = loader.getController();
        controller.Setglowny(this);
        
        stage.hide();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    public void showDialogEdycja(Osoba osoba) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Edycja.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edycja");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        Edycja controller = loader.getController();
        controller.setDialog(dialogStage);
        controller.setOsoba(osoba);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
    }
    public boolean showDialogRejestracja(Osoba osoba) throws Exception {
        try {
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
        controller.setOsoba(osoba);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
        return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showDialogProjekt(Projekt projekt) throws Exception {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno_Projekt.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Nowy Projekt");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        Okno_Projekt controller = loader.getController();
        controller.setDialog(dialogStage);
        controller.setProjekt(projekt);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
        return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showDialogSprint(Sprint sprint) throws Exception {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno_Sprintu.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Nowy Sprint");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        Okno_Sprintu controller = loader.getController();
        controller.setDialog(dialogStage);
        controller.setSprint(sprint);
        controller.setController(this);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
        return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showDialogZadanie(Zadanie zadanie) throws Exception {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Okno_Zadanie.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Nowe Zadanie");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        Okno_Zadanie controller = loader.getController();
        controller.setDialog(dialogStage);
        controller.setZadanie(zadanie);
        controller.setController(this);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
        return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
}
