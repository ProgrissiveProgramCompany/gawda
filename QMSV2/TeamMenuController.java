/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Isra
 */
 
public class TeamMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Message m ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    //handle report button
    public void handleActionB1(){
       m = new Message();
       m.service = 1;
       loadTo("TeamReport.fxml");
    }
    //handle evidanve button
    public void handleActionB2(){}
    //handle improvement button
    public void handleActionB3(){}
    //handle back button
    public void handleActionB4(){}
    
    
    public void loadTo(String className){
        Stage window = new Stage();
        Parent root = null;
        try {
            StaticIO.os.writeObject(m); 
            root = FXMLLoader.load(getClass().getResource(className));   
        } catch (IOException ex) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }    
    
    
}
