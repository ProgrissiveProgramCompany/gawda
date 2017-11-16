/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;
//os
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Isra
 */
public class AddAccountController implements Initializable {

    //ObjectOutputStream os ;
    //ObjectInputStream is ;
    @FXML ComboBox combo ;
    @FXML TextField username ;
    @FXML TextField password ;
    @FXML Button btn1;
    ArrayList array   ;
    Message m ;
    
    final ObservableList option = FXCollections.observableArrayList();

   /* AddAccountController(ObjectOutputStream os, ObjectInputStream is) {
        this.os = os ;
        this.is = is ;
          }*/
 
    @FXML
    private void handleActionB1(){
         Stage window = (Stage) btn1.getScene().getWindow();
         window.close();
    
    }    
    @FXML
    private void handleActionB2(){
        m = new Message();
        m.array = new ArrayList();
        String coll = combo.getSelectionModel().getSelectedItem().toString();
        m.array.add(coll);
        System.out.println(coll+"11"+m.array.get(0));
        String use = (String)username.getText();
        m.array.add(use);
                System.out.println(use+"11"+m.array.get(1));
        String pass = (String)password.getText();
        m.array.add(pass);
                System.out.println(pass+"11"+m.array.get(2));

       // m.array= array;
        m.service=11;
        
        if(coll.equals(null) || use.equals(null) || pass.equals(null)){
            JOptionPane.showMessageDialog(null,"all feild is required");

        }else{
               
           try {
             StaticIO.os.writeObject(m);
           } catch (IOException ex) {
            ex.printStackTrace();
           }      
            JOptionPane.showMessageDialog(null,"added successfully");
        }
            
           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        array = new ArrayList();
        m = new Message(); 
        try {
          System.out.println(isNull(StaticIO.is)+"hena");
            m = (Message)StaticIO.is.readObject();  
            System.out.println(isNull(m));
            array = m.array ;
            System.out.println(isNull(array));

            for(int i = 0 ; i < 2 ; i++)
             System.out.println((m.array.get(i)));

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            for(int i = 0 ; i<array.size();i++){
               option.add((String)array.get(i));
            } 
          
            combo.setItems(option);
            
            
    }    

    
}
