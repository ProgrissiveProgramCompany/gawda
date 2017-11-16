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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import static qmsv2.LoginController.is;

/**
 * FXML Controller class
 *
 * @author Isra
 */
public class RemoveAccountController implements Initializable {
    ObjectOutputStream os ;
    ObjectInputStream is ;
    ArrayList array ;
    @FXML Button btn1;
    @FXML ComboBox combo ;
    @FXML ComboBox combo2 ;
    final ObservableList collName = FXCollections.observableArrayList();
    final ObservableList accName = FXCollections.observableArrayList();


    Message m ;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       System.out.println("borrrrrrrrrrrrrrrrrrrrrrring");
        /* array = new ArrayList();
        m = new Message();
        try {
     
            m = (Message)is.readObject();
            array = m.array;
            System.out.println("asdsadsdadadadssd"+array.size());
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(RemoveAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RemoveAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            for(int i = 0 ; i<array.size();i++){
               collName.add((String)((ArrayList)array.get(i)).get(0));
            } 
            int coll = combo.getSelectionModel().getSelectedIndex();
            for(int i = 1 ; i<array.size();i++){
               
               accName.add((String)((ArrayList)array.get(coll)).get(i));
            } */
           // combo.setItems(collName);
           // combo2.setItems(accName);
     
        
   
     
    }  
    
    @FXML 
    private void handlleActionB1(){
        Stage window = (Stage) btn1.getScene().getWindow();
        window.close();   
    
    }
    @FXML 
    private void handleActionB2(){
        m = new Message() ;
       String coll = combo.getSelectionModel().getSelectedItem().toString();
       String acc = combo2.getSelectionModel().getSelectedItem().toString();
       m.array.add(coll);
       m.array.add(acc);
       try {
           StaticIO.os.writeObject(m); 
        } catch (Exception ex) {
        ex.printStackTrace();
        }  
       
    }    
    
}
