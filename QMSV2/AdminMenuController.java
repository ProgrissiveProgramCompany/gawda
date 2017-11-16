/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Objects.isNull;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static qmsv2.LoginController.setIO;

/**
 *
 * @author Isra
 */
public class AdminMenuController implements Initializable{
    
    @FXML Button btn1;
    
   // ObjectOutputStream os;
   // ObjectInputStream is;
   
    Message m ;
    

    /*
    public void setIO(ObjectOutputStream os,ObjectInputStream is)throws Exception{
   
        this.os = os ;
        this.is = is ;
        System.out.println(isNull(this.os));

    }
     */
    @FXML
    private void handleActionB1(){
        Stage window = (Stage) btn1.getScene().getWindow();
        window.close();
    }
    //handle the action of add button
    @FXML
    private void handleActionb2(){
        m = new Message();
        m.service = 1;
        loadTo("AddAccount.fxml");
    }
    //handle the action of remove button 
    @FXML
    private void handleActionb3(){
        m = new Message();
        m.service = 2;    
        loadTo("RemoveAccount.fxml");        
    }   
    @FXML
    private void handleActionb4(){}
    
    public void loadTo(String className){
        
        Stage window = new Stage();
        Parent root = null;
        
    
                try {
             try {
                 StaticIO.os.writeObject(m);     
             } catch (Exception ex) {
                 Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             root = FXMLLoader.load(getClass().getResource(className));
             
         } catch (IOException ex) {
             Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
         }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
}
